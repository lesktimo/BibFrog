package bibfrog.controller;

import bibfrog.domain.Inproceeding;
import bibfrog.repositories.InproceedingsRepo;
import bibfrog.service.ExportService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InproceedingsController {

    @Autowired
    private InproceedingsRepo inproRepo;

    @Autowired
    private ExportService exportService;

    @RequestMapping(value = "/inpro/add", method = RequestMethod.GET)
    public String addInproceeding(Model model) {
        model.addAttribute("inproceeding", new Inproceeding());
        return "inpro";
    }

    @RequestMapping(value = "/inpro/add", method = RequestMethod.POST)
    public String postInproceeding(@Valid @ModelAttribute Inproceeding inpro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "inpro";
        }
        Inproceeding helperInpro = inproRepo.save(inpro);
        helperInpro.setAuthors();
        inproRepo.save(helperInpro);
        return "redirect:/inpros";
    }

    @RequestMapping(value = "/inpros", method = RequestMethod.GET)
    public String listInproceedings(Model model) {
        model.addAttribute("inproList", inproRepo.findAll());
        return "inpros";
    }

    @RequestMapping(value = "/inpro/{id}/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadInpro(@PathVariable Long id, @RequestParam String fileName) throws IOException {
        if (fileName.isEmpty()) {fileName = "fugyou";}
        createFileForDownloading(id);
        File inproFile = getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(createPath(inproFile));
        return new HttpEntity<>(bytes, createHeaders(inproFile, fileName));
    }

    private File getFilePathForBytes(String filePath) {
        File inproFile = new File(filePath);
        return inproFile;
    }

    private void createFileForDownloading(Long id) throws IOException {
        Inproceeding inpro = inproRepo.findOne(id);
        String bibtex = exportService.createBibtexFromInproceeding(inpro);
        exportService.createFile(bibtex);
    }

    private Path createPath(File inproFile) {
        Path path = Paths.get(inproFile.getPath());
        return path;
    }

    private HttpHeaders createHeaders(File inproFile, String fileName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename="+ fileName +".bib".replace(".txt", ""));
        headers.setContentLength(inproFile.length());
        return headers;
    }

}
