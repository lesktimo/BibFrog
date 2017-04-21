package bibfrog.service;

import bibfrog.domain.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportService {

    @Autowired
    private ScandicService scandic;

    public void createFile(String bibtex) throws IOException {
        File file = new File("src/bibtex.bib");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(bibtex);
        fileWriter.flush();
        fileWriter.close();
    }

    public String createBibtexFromInproceeding(Inproceeding inpro) {
        String bibtex = "@inproceedings{" + scandic.scandicChecker(inpro.getReferenceKey()) + ","
                + "\nauthor = {" + scandic.scandicChecker(inpro.authorString()) + "},"
                + "\ntitle = {" + scandic.scandicChecker(inpro.getTitle()) + "},"
                + "\nbooktitle = {" + scandic.scandicChecker(inpro.getBookTitle()) + "},"
                + "\nyear = {" + inpro.getPublishYear() + "}";
        bibtex += addOptionalFieldsToBibtex(inpro);
        bibtex += "\n}";
        return bibtex;
    }

    public String createBibtexFromBook(Book book) {
        String bibtex = "@book{" + scandic.scandicChecker(book.getReferenceKey()) + ","
                + "\nauthor = {" + scandic.scandicChecker(book.authorString()) + "},"
                + "\ntitle = {" + scandic.scandicChecker(book.getTitle()) + "},"
                + "\npublisher = {" + scandic.scandicChecker(book.getPublisher()) + "},"
                + "\nyear = {" + book.getPublishYear() + "}";

        bibtex += addOptionalFieldsToBibtex(book);

        bibtex += "\n}";
        return bibtex;
    }

    public String createBibtexFromArticle(Article article) {
        String bibtex = "@article{" + scandic.scandicChecker(article.getReferenceKey()) + ","
                + "\nauthor = {" + scandic.scandicChecker(article.authorString()) + "},"
                + "\ntitle = {" + scandic.scandicChecker(article.getTitle()) + "},"
                + "\njournal = {" + scandic.scandicChecker(article.getJournal()) + "},"
                + "\nyear = {" + article.getPublishYear() + "}";

        bibtex += addOptionalFieldsToBibtex(article);

        bibtex += "\n}";
        return bibtex;
    }

    public String addOptionalFieldsToBibtex(Reference ref) {
        HashMap<String, String> optionalFields = ref.optionalFields();
        String inproTex = "";

        for (Entry entry : optionalFields.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().equals("") && !entry.getValue().equals("0")) {
                inproTex += ",\n" + entry.getKey() + " = {" + scandic.scandicChecker((String) entry.getValue()) + "}";
            }
        }
        return inproTex;
    }
}
