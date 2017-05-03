package bibfrog.controller;

import bibfrog.domain.Reference;
import bibfrog.repositories.*;
import bibfrog.service.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReferenceController {

    @Autowired
    private InproceedingsRepo inprosRepo;

    @Autowired
    private BooksRepo booksRepo;

    @Autowired
    private ArticleRepo articlesRepo;

    @Autowired
    private ExportService exportService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAllReferences(Model model) {
        List<Reference> references = getAllReferences();
        model.addAttribute("list", references);
        return "list";
    }

    @RequestMapping(value = "/list/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadAll(@RequestParam String fileName) throws IOException {
        if (fileName.isEmpty()) {
            fileName = "fugyou";
        }
        createFileForDownloading();
        return fileService.createBibFile(fileName);

    }

    private List<Reference> getAllReferences() {
        List<Reference> references = new ArrayList<>();
        references.addAll(articlesRepo.findAll());
        references.addAll(booksRepo.findAll());
        references.addAll(inprosRepo.findAll());
        return references;
    }

    private void createFileForDownloading() throws IOException {
        String bibtex = exportService.createBibtexFromAll(inprosRepo.findAll(), booksRepo.findAll(), articlesRepo.findAll());
        exportService.createFile(bibtex);
    }
}
