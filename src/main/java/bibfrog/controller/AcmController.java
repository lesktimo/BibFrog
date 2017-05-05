package bibfrog.controller;

import bibfrog.service.FileService;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpHeaders.USER_AGENT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AcmController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/acm", method = RequestMethod.GET)
    public String createBibtexFromAcm(@RequestParam String queryACM) throws MalformedURLException, IOException {
        String searchUrl = "http://dl.acm.org/downformats.cfm?id=";
        String loppuOsa = "&parent_id=&expformat=bibtex";
        URL url = new URL(searchUrl + queryACM + loppuOsa);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        if (conn.getResponseCode() != HttpStatus.NOT_FOUND_404) {
            if (fileService.parseACMResponse(conn.getInputStream())) {
                return "redirect:/list";
            }
            return "redirect:/search?query=";
        }
        return "redirect:/list";
    }
}
