package bibfrog.service;

import bibfrog.domain.Article;
import bibfrog.domain.Book;
import bibfrog.domain.Inproceeding;
import bibfrog.domain.Reference;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;

@Service
public class ExportService {

    public void createFile(String bibtex) throws IOException {
        File file = new File("bibtex.bib");
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(bibtex);

        fileWriter.flush();
        fileWriter.close();

    }

    
    public void scandicChecker(String bibtex) throws FileNotFoundException, IOException{
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(bibtex),Charset.forName("UTF-8")));
    int c;
    while((c = reader.read()) != -1) {
    char character = (char) c;
    if(c=='ö') {
        Character.toString(character);
        
        //replace with \"o        
    } else if(c=='ä') {
        
       //replace with \"a     
    } else if(c=='å') {
        //replace with \aa
    } else if(c=='Ö') {
        //replace with \"O    
    } else if(c=='Ä') {
        //replace with \"a
    } else if(c=='Å') {
        //replace with \AA
    }
    }
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

    public String addOptionalFieldsToBibtex(Reference ref) {
        HashMap<String, String> optionalFields = ref.optionalFields();
        String inproTex = "";

        for (Entry entry : optionalFields.entrySet()) {
            if (entry.getValue() != null) {
                inproTex += ",\n    " + entry.getKey() + "= {" + entry.getValue() + "}";
            }
        }
        return inproTex;
    }

    public String createBibtexFromBookFile(Book book) {

        String bibtex = "@Books{" + book.getReferenceKey() + ","
                + "\n   author = {" + book.getAuthor() + "},"
                + "\n   title = {" + book.getTitle() + "},"
                + "\n   booktitle = {" + book.getPublisher() + "},"
                + "\n   year = {" + book.getPublishYear() + "}";

        bibtex += addOptionalFieldsToBibtex(book);

        bibtex += "\n}";
        return bibtex;
    }

    public String createBibtexFromArticleFile(Article article) {
        return "";
    }

}
