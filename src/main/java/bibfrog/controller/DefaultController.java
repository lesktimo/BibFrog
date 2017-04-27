package bibfrog.controller;

import bibfrog.domain.Reference;
import bibfrog.repositories.ArticleRepo;
import bibfrog.repositories.BooksRepo;
import bibfrog.repositories.InproceedingsRepo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
public class DefaultController{

    @Autowired
    private BooksRepo books;
    @Autowired
    private ArticleRepo articles;
    @Autowired
    private InproceedingsRepo inpros;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultMapping() {
        return "home";
    }

    

//    @RequestMapping(value = "/list/download", method = RequestMethod.GET)
//    public HttpEntity<byte[]> downloadInpro(@RequestParam String fileName) throws IOException {
//        if (fileName.isEmpty()) {
//            fileName = "fugyou";
//        }
//        //TODO exportService createFileForMultipleReference(getAllReferences());
//        File inproFile = getFilePathForBytes("src/bibtex.bib");
//        byte[] bytes = Files.readAllBytes(createPath(inproFile));
//        return new HttpEntity<>(bytes, createHeaders(inproFile, fileName));
//    }
}
