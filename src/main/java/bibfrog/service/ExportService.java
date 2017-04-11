
package bibfrog.service;

import bibfrog.domain.Article;
import bibfrog.domain.Book;
import bibfrog.domain.Inproceeding;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;

@Service
public class ExportService {
 
    
    public void createInproceedingFile(String bibtex) throws IOException{
        
        
        File file = new File("bibtex.bib");
        file.createNewFile();
        
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(bibtex);
        
        fileWriter.flush();
        fileWriter.close();
        
    }
    
    
    public String createBibtexFromInproceeding(Inproceeding inpro) {
        
        String bibtex = "@inproceedings{" + inpro.getReferenceKey() + ","
                + "\n   author = {" + inpro.authorString() + "},"
                + "\n   title = {" + inpro.getTitle() + "},"
                + "\n   booktitle = {" + inpro.getBookTitle() + "},"
                + "\n   year = {" + inpro.getPublishYear() + "}";
        
        bibtex += addOptionalFieldsToBibtex(inpro);
        
        bibtex += "\n}";
        
        
        return bibtex;
    }
    
    public String addOptionalFieldsToBibtex ( Inproceeding inpro) {
        HashMap<String, String> optionalFields = inpro.optionalFields();
        String inproTex = "";
        
        for (Entry entry : optionalFields.entrySet()) {
            if (entry.getValue() != null) {
                inproTex += ",\n    " + entry.getKey() + "= {" + entry.getValue() + "}";
            }
        }
        return inproTex;
    }
    
    
    
    public void createBookFile (Book book){
        
    }
    
    public void createArticleFile(Article article){
        
    }
    
    
}
