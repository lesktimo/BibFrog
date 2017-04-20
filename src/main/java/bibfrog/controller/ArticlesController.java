package bibfrog.controller;

import bibfrog.domain.Article;
import bibfrog.repositories.ArticleRepo;
import bibfrog.service.ExportService;
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
public class ArticlesController extends ReferanceController {

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

    @RequestMapping(value = "/article/{id}/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadArticle(@PathVariable Long id, @RequestParam String fileName) throws IOException {
        createFileForDownloading(id);
        File articleFile = getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(createPath(articleFile));
        return new HttpEntity<>(bytes, createHeaders(articleFile, fileName));
    }

    private void createFileForDownloading(Long id) throws IOException {
        Article article = articleRepo.findOne(id);
        String bibtex = exportService.createBibtexFromArticleFile(article);
        exportService.createFile(bibtex);
    }

}
