package bibfrog.controller;

import bibfrog.domain.Article;
import bibfrog.repositories.ArticleRepo;
import bibfrog.service.ExportService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ArticlesController {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private ExportService exportService;

    @RequestMapping(value = "/article/add", method = RequestMethod.GET)
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "article";
    }

    @RequestMapping(value = "/article/add", method = RequestMethod.POST)
    public String postArticle(@Valid @ModelAttribute Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article";
        }
        articleRepo.save(article);
        return "redirect:/articles";
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String listArticles(Model model) {
        model.addAttribute("articleList", articleRepo.findAll());
        return "articles";
    }

}
