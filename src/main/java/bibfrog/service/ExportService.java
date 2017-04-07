
package bibfrog.service;

import bibfrog.domain.Article;
import bibfrog.domain.Book;
import bibfrog.domain.Inproceeding;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class ExportService {
 
    
    public void createInproceedingFile(Inproceeding inpro) throws IOException{
        
        String bibtex = "@inproceedings{" + inpro.getReferenceKey() + "}\nauthor = {" + inpro.authorString() + "}, \ntitle = {" + inpro.getTitle() + "}"; 
        File file = new File("bibtex.bib");
        file.createNewFile();
        
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(bibtex);
        
        fileWriter.flush();
        fileWriter.close();
        
    }
    
    public void createBookFile (Book book){
        
    }
    
    public void createArticleFile(Article article){
        
    }
    
    
}
