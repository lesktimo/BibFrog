package bibfrog.controller;

import bibfrog.domain.Inproceeding;
import bibfrog.repositories.InproceedingsRepo;
import bibfrog.service.*;
import java.io.IOException;
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
        return setInproAttributes(inpro);
    }

    @RequestMapping(value = "/inpro/add", method = RequestMethod.POST)
    public String postInproceeding(@Valid @ModelAttribute Inproceeding inpro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "inpro";
        }
        return setInproAttributes(inpro);
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
        return fileService.createBibFile(fileName);

    }

    @RequestMapping(value = "/inpros/all/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadAllInpros(@RequestParam String fileName) throws IOException {
        String bibtex = exportService.createBibtexFromAllInproceedings(inproRepo.findAll());
        exportService.createFile(bibtex);
        return fileService.createBibFile(fileName);

    }

    private void createFileForDownloading(Long id) throws IOException {
        Inproceeding inpro = inproRepo.findOne(id);
        String bibtex = exportService.createBibtexFromInproceeding(inpro);
        exportService.createFile(bibtex);
    }

    private String setInproAttributes(Inproceeding inpro) {
        inpro = inproRepo.save(inpro);
        if (inpro.getReferenceKey() == null || inpro.getReferenceKey().isEmpty()) {
            inpro.generateReferenceKey();
            inproRepo.save(inpro);
        }
        return "redirect:/inpros";
    }

}
