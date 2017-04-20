package bibfrog.service;

import bibfrog.domain.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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

    public String createBibtexFromBookFile(Book book) {
        String bibtex = "@book{" + book.getReferenceKey() + ","
                + "\n   author = {" + book.getAuthor() + "},"
                + "\n   title = {" + book.getTitle() + "},"
                + "\n   publisher = {" + book.getPublisher() + "},"
                + "\n   year = {" + book.getPublishYear() + "}";

        bibtex += addOptionalFieldsToBibtex(book);

        bibtex += "\n}";
        return bibtex;
    }

    public String createBibtexFromArticleFile(Article article) {
        String bibtex = "@article{" + article.getReferenceKey() + ","
                + "\n   author = {" + article.getAuthor() + "},"
                + "\n   title = {" + article.getTitle() + "},"
                + "\n   journal = {" + article.getJournal() + "},"
                + "\n   year = {" + article.getPublishYear() + "}";

        bibtex += addOptionalFieldsToBibtex(article);

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
}
