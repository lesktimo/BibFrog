package bibfrog.controller;

import bibfrog.domain.Reference;
import bibfrog.repositories.*;
import bibfrog.service.ExportService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReferenceController {

    @Autowired
    private InproceedingsRepo inpros;

    @Autowired
    private BooksRepo books;

    @Autowired
    private ArticleRepo articles;

    @Autowired
    private ExportService exportService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAllReferences(Model model) {
        List<Reference> references = getAllReferences();
        model.addAttribute("list", references);
        return "list";
    }

    private List<Reference> getAllReferences() {
        List<Reference> references = new ArrayList<>();
        references.addAll(articles.findAll());
        references.addAll(books.findAll());
        references.addAll(inpros.findAll());
        return references;
    }

    @RequestMapping(value = "/list/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadAll(@RequestParam String fileName) throws IOException {
        if (fileName.isEmpty()) {
            fileName = "fugyou";
        }
        createFileForDownloading();
        File inproFile = getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(createPath(inproFile));
        return new HttpEntity<>(bytes, createHeaders(inproFile, fileName));
    }

    public void createFileForDownloading() throws IOException {
        String bibtex = exportService.createBibtexFromAll(inpros.findAll(), books.findAll(), articles.findAll());
        exportService.createFile(bibtex);
    }

    protected File getFilePathForBytes(String filePath) {
        return new File(filePath);

    }

    protected Path createPath(File file) {
        return Paths.get(file.getPath());
    }

    protected HttpHeaders createHeaders(File file, String fileName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName + ".bib".replace(".txt", ""));
        headers.setContentLength(file.length());
        return headers;
    }

}
