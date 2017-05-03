package bibfrog.controller;

import bibfrog.domain.Reference;
import bibfrog.repositories.ArticleRepo;
import bibfrog.repositories.BooksRepo;
import bibfrog.repositories.InproceedingsRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private ArticleRepo aRepo;
    @Autowired
    private BooksRepo bRepo;
    @Autowired
    private InproceedingsRepo iRepo;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchSite(@RequestParam String query, Model model) {
        List<Reference> results = new ArrayList<>();
        results.addAll(aRepo.findByTitleContainingIgnoringCase(query));
        model.addAttribute("results", results);
        return "results";
    }
}
