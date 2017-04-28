package bibfrog.controller;

import bibfrog.domain.Inproceeding;
import bibfrog.repositories.InproceedingsRepo;
import bibfrog.service.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/inpro/add", method = RequestMethod.GET)
    public String addInproceeding(Model model) {
        model.addAttribute("inproceeding", new Inproceeding());
        return "inpro";
    }

    @RequestMapping(value = "/inpro/{id}/edit", method = RequestMethod.GET)
    public String editInproceeding(@PathVariable Long id, Model model) {
        Inproceeding inpro = inproRepo.findOne(id);
        model.addAttribute("inproceeding", inpro);
        return "inpro_edit";
    }

    @RequestMapping(value = "/inpro/{id}/edit", method = RequestMethod.POST)
    public String updateInproceeding(@PathVariable Long id, @Valid @ModelAttribute Inproceeding inpro, BindingResult bindingResult) {
        inproRepo.delete(id);

        if (bindingResult.hasErrors()) {
            return "inpro_edit";
        }
        inpro = inproRepo.save(inpro);
        inpro.setAuthors();
        if (inpro.getReferenceKey() == null || inpro.getReferenceKey().isEmpty()) {
            inpro.generateReferenceKey();
        }

        inproRepo.save(inpro);
        return "redirect:/inpros";
    }

    @RequestMapping(value = "/inpro/add", method = RequestMethod.POST)
    public String postInproceeding(@Valid @ModelAttribute Inproceeding inpro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "inpro";
        }
        inpro = inproRepo.save(inpro);
        inpro.setAuthors();
        if (inpro.getReferenceKey() == null || inpro.getReferenceKey().isEmpty()) {
            inpro.generateReferenceKey();
        }
        inproRepo.save(inpro);
        return "redirect:/inpros";
    }

    @RequestMapping(value = "/inpros", method = RequestMethod.GET)
    public String listInproceedings(Model model) {
        model.addAttribute("inproList", inproRepo.findAll());
        return "inpros";
    }

    @RequestMapping(value = "/inpro/{id}/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadInpro(@PathVariable Long id, @RequestParam String fileName) throws IOException {
        if (fileName.isEmpty()) {
            fileName = "fugyou";
        }
        createFileForDownloading(id);
        File inproFile = fileService.getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(fileService.createPath(inproFile));
        return new HttpEntity<>(bytes, fileService.createHeaders(inproFile, fileName));
    }

    @RequestMapping(value = "/inpros/all/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadAllInpros(@RequestParam String fileName) throws IOException {
        String bibtex = exportService.createBibtexFromAllInproceedings(inproRepo.findAll());
        exportService.createFile(bibtex);
        File inproFile = fileService.getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(fileService.createPath(inproFile));
        return new HttpEntity<>(bytes, fileService.createHeaders(inproFile, fileName));
    }
    
    private void createFileForDownloading(Long id) throws IOException {
        Inproceeding inpro = inproRepo.findOne(id);
        String bibtex = exportService.createBibtexFromInproceeding(inpro);
        exportService.createFile(bibtex);
    }

}
