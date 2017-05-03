package bibfrog.controller;

import bibfrog.domain.Reference;
import bibfrog.repositories.ArticleRepo;
import bibfrog.repositories.BooksRepo;
import bibfrog.repositories.InproceedingsRepo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        if (!aRepo.findAll().isEmpty()) {
            results.addAll(aRepo.findByTitleContainingIgnoringCase(query));
            results.addAll(aRepo.findByGivenAuthorsContainingIgnoringCase(query));
            results.addAll(aRepo.findByJournalContainingIgnoringCase(query));
            if (query.matches("[0-9+]")) {
                results.addAll(aRepo.findByPublishYear(Integer.parseInt(query)));
            }
        }
        if (!bRepo.findAll().isEmpty()) {
            results.addAll(bRepo.findByTitleContainingIgnoringCase(query));
            results.addAll(bRepo.findByGivenAuthorsContainingIgnoringCase(query));
            results.addAll(bRepo.findByPublisherContainingIgnoringCase(query));
            if (query.matches("[0-9+]")) {
                results.addAll(bRepo.findByPublishYear(Integer.parseInt(query)));
            }
        }
        if (!iRepo.findAll().isEmpty()) {
            results.addAll(iRepo.findByTitleContainingIgnoringCase(query));
            results.addAll(iRepo.findByGivenAuthorsContainingIgnoringCase(query));
            results.addAll(iRepo.findByPublisherContainingIgnoringCase(query));
            results.addAll(iRepo.findByBookTitleContainingIgnoringCase(query));
            results.addAll(iRepo.findByEditorContainingIgnoringCase(query));
            if (query.matches("[0-9+]")) {
                results.addAll(iRepo.findByPublishYear(Integer.parseInt(query)));
            }
        }
        Set<Reference> s = new HashSet<>();
        s.addAll(results);
        results.clear();
        results.addAll(s);
        model.addAttribute("results", s);
        return "results";
    }
}
