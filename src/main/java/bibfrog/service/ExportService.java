package bibfrog.service;

import bibfrog.domain.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;

@Service
public class ExportService {

    public void createFile(String bibtex) throws IOException {
        File file = new File("src/bibtex.bib");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(bibtex);
        fileWriter.flush();
        fileWriter.close();
    }
    
    
    public String createBibtexFromAll(List<Inproceeding> inpros, List<Book> books, List<Article> articles){
        String bibtex = "";
        
        bibtex += createBibtexFromAllInproceedings(inpros);
        bibtex += createBibtexFromAllBooks(books);
        bibtex += createBibtexFromAllArticles(articles);
        
        return bibtex;
    }
    
    public String createBibtexFromAllInproceedings(List<Inproceeding> inpros){
        String inprotex = "";
        for (Inproceeding inpro : inpros) {
            inprotex += createBibtexFromInproceeding(inpro);
            inprotex += "\n";
            inprotex += "\n";
        }
        
        return inprotex;
    }
    
    public String createBibtexFromAllBooks(List<Book> books){
        String booktex = "";
        for (Book book : books) {
            booktex += createBibtexFromBook(book);
            booktex += "\n";
            booktex += "\n";
        }
        
        return booktex;
    }
    
    
    public String createBibtexFromAllArticles(List<Article> articles){
        String articletex = "";
        for (Article article : articles) {
            articletex += createBibtexFromArticle(article);
            articletex += "\n";
            articletex += "\n";
        }
        
        return articletex;
    }
    
    
    
    

    public String createBibtexFromInproceeding(Inproceeding inpro) {
        String bibtex = "@inproceedings{" + scandicChecker(inpro.getReferenceKey()) + ","
                + "\nauthor = {" + scandicChecker(inpro.authorString()) + "},"
                + "\ntitle = {" + scandicChecker(inpro.getTitle()) + "},"
                + "\nbooktitle = {" + scandicChecker(inpro.getBookTitle()) + "},"
                + "\nyear = {" + inpro.getPublishYear() + "}";
        bibtex += addOptionalFieldsToBibtex(inpro);
        bibtex += "\n}";
        return bibtex;
    }

    public String createBibtexFromBook(Book book) {
        String bibtex = "@book{" + scandicChecker(book.getReferenceKey()) + ","
                + "\nauthor = {" + scandicChecker(book.authorString()) + "},"
                + "\ntitle = {" + scandicChecker(book.getTitle()) + "},"
                + "\npublisher = {" + scandicChecker(book.getPublisher()) + "},"
                + "\nyear = {" + book.getPublishYear() + "}";

        bibtex += addOptionalFieldsToBibtex(book);

        bibtex += "\n}";
        return bibtex;
    }

    public String createBibtexFromArticle(Article article) {
        String bibtex = "@article{" + scandicChecker(article.getReferenceKey()) + ","
                + "\nauthor = {" + scandicChecker(article.authorString()) + "},"
                + "\ntitle = {" + scandicChecker(article.getTitle()) + "},"
                + "\njournal = {" + scandicChecker(article.getJournal()) + "},"
                + "\nyear = {" + article.getPublishYear() + "}";

        bibtex += addOptionalFieldsToBibtex(article);

        bibtex += "\n}";
        return bibtex;
    }

    public String addOptionalFieldsToBibtex(Reference ref) {
        LinkedHashMap<String, String> optionalFields = ref.optionalFields();
        String inproTex = "";

        for (Entry entry : optionalFields.entrySet()) {
            String value = (String) entry.getValue();
            if (value != null && !value.equals("") && !value.equals("0")) {
                inproTex += ",\n" + entry.getKey() + " = {" + scandicChecker(value) + "}";
            }
        }
        return inproTex;
    }

    public String scandicChecker(String bibtex) {

        int i = 0;
        int j = 0;
        String newScandicBibtex = "";
        for (char c : bibtex.toCharArray()) {

            switch (c) {
                case 'ö':
                    newScandicBibtex += bibtex.substring(j, i) + "\\\"o";
                    j = i + 1;
                    //replace with \"o        
                    break;
                case 'ä':
                    newScandicBibtex += bibtex.substring(j, i) + "\\\"a";
                    j = i + 1;
                    //replace with \"a     
                    break;
                case 'å':
                    newScandicBibtex += bibtex.substring(j, i) + "\\aa";
                    j = i + 1;
                    //replace with \aa
                    break;
                case 'Ö':
                    newScandicBibtex += bibtex.substring(j, i) + "\\\"O";
                    j = i + 1;
                    //replace with \"O    
                    break;
                case 'Ä':
                    newScandicBibtex += bibtex.substring(j, i) + "\\\"A";
                    j = i + 1;
                    //replace with \"a
                    break;
                case 'Å':
                    newScandicBibtex += bibtex.substring(j, i) + "\\AA";
                    j = i + 1;
                    //replace with \AA
                    break;
                default:
                    break;
            }
            i++;
        }
        newScandicBibtex += bibtex.substring(j);
        return newScandicBibtex;
    }
}
