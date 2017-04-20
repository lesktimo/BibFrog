package bibfrog.controller;

import bibfrog.domain.Inproceeding;
import bibfrog.repositories.InproceedingsRepo;
import bibfrog.service.ExportService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

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
    public HttpEntity<byte[]> downloadInpro(@PathVariable Long id, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
        Inproceeding inpro = inproRepo.findOne(id);
        String bibtex = exportService.createBibtexFromInproceeding(inpro);
        exportService.createFile(bibtex);
        
        final HttpHeaders headers = new HttpHeaders();
        String filePath = "src/bibtex.bib";
        File inproFile = new File(filePath);
        
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, 
                "attachment; filename=bibtex.bib".replace(".txt", ""));
        headers.setContentLength(inproFile.length());
        Path path = Paths.get(inproFile.getPath()); 
        byte[] bytes = Files.readAllBytes(path);
        
        return new HttpEntity<>(bytes, headers);

    }

}
