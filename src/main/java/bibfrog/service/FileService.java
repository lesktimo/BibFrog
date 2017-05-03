package bibfrog.service;

import bibfrog.domain.Inproceeding;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    
    

    public File getFilePathForBytes(String filePath) {
        return new File(filePath);

    }

    public Path createPath(File file) {
        return Paths.get(file.getPath());
    }

    public HttpHeaders createHeaders(File file, String fileName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName + ".bib".replace(".txt", ""));
        headers.setContentLength(file.length());
        return headers;
    }
    
    public Inproceeding createInproceedingFromFile(File inpro) throws FileNotFoundException {
         Scanner fileReader = new Scanner(inpro);
        Inproceeding newInpro = new Inproceeding();
        String inprotex = "";
        
        while(fileReader.hasNextLine()){
            inprotex += fileReader.nextLine();
        }
        
        String[] attributes = inprotex.split(",");
        
        for (String attribute : attributes) {
            System.out.println(attribute);
        }
        
        
        return newInpro;
    }

}
