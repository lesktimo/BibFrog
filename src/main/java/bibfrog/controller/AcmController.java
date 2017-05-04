package bibfrog.controller;

import bibfrog.service.FileService;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//http://dl.acm.org/downformats.cfm?id=2485620&parent_id=&expformat=bibtex
@Controller
public class AcmController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/testaus", method = RequestMethod.GET)
    public String createBibtexFromAcm(@RequestParam String searchUrl) throws MalformedURLException, IOException {
        searchUrl = URLEncoder.encode(searchUrl, "utf-8");
        URL url = new URL(searchUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        File acmFile = new File("src/acm.txt");
        FileUtils.copyInputStreamToFile(conn.getInputStream(), acmFile);

        Scanner fr = new Scanner(acmFile);
        String firstLine = fr.nextLine();

        if (firstLine.contains("@book")) {

        } else if (firstLine.contains("@article")) {

        } else if (firstLine.contains("@inproceedings")) {
            fileService.createInproceedingFromFile(acmFile);
        }

        return "list";
    }
}
