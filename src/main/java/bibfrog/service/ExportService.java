package bibfrog.service;

import bibfrog.domain.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;

/**
 * Service for creating bibtex.bib from references.
 */
@Service
public class ExportService {

    /**
     * Creates a bibtex.bib file to src from bibtex-string.
     *
     * @param bibtex bibtex-string of reference
     * @throws IOException
     */
    public void createFile(String bibtex) throws IOException {
        File file = new File("src/bibtex.bib");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(bibtex);
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Creates bibtex-string from all references to be exported.
     *
     * @param inpros all inproceedings
     * @param books all books
     * @param articles all articles
     * @return string of all references in bibtex-format
     */
    public String createBibtexFromAll(List<Inproceeding> inpros, List<Book> books, List<Article> articles) {
        String bibtex = "";

        bibtex += createBibtexFromAllInproceedings(inpros);
        bibtex += createBibtexFromAllBooks(books);
        bibtex += createBibtexFromAllArticles(articles);

        return bibtex;
    }

    /**
     * Creates bibtex-string from all inproceedings to be exported.
     *
     * @param inpros all inproceedings
     * @return string of all inproceedings in bibtex-format
     */
    public String createBibtexFromAllInproceedings(List<Inproceeding> inpros) {
        String inprotex = "";
        for (Inproceeding inpro : inpros) {
            inprotex += createBibtexFromInproceeding(inpro) + "\n\n";
        }

        return inprotex.trim();
    }

    /**
     * Creates bibtex-string from all books to be exported.
     *
     * @param books all books
     * @return string of all books in bibtex-format
     */
    public String createBibtexFromAllBooks(List<Book> books) {
        String booktex = "";
        for (Book book : books) {
            booktex += createBibtexFromBook(book) + "\n\n";
        }

        return booktex.trim();
    }

    /**
     * Creates bibtex-string from all articles to be exported.
     *
     * @param articles all articles
     * @return string of all articles in bibtex-format
     */
    public String createBibtexFromAllArticles(List<Article> articles) {
        String articletex = "";
        for (Article article : articles) {
            articletex += createBibtexFromArticle(article) + "\n\n";
        }

        return articletex.trim();
    }

    /**
     * Creates bibtex-string from inproceeding to be exported.
     *
     * @param inpro inproceeding
     * @return string of inproceeding in bibtex-format
     */
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

    /**
     * Creates bibtex-string from book to be exported.
     *
     * @param book book
     * @return string of book in bibtex-format
     */
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

    /**
     * Creates bibtex-string from article to be exported.
     *
     * @param article article
     * @return string of article in bibtex-format
     */
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

    /**
     * Adds all present optional attributes from reference to bibtex-string.
     *
     * @param ref reference
     * @return string of reference in bibtex-format with optional fields added
     */
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

    /**
     * Checks bibtex-string for scandic letters and refactors to latex-format.
     *
     * @param bibtex bibtex-string of reference
     * @return bibtex-string with latex friendly scandics
     */
    public String scandicChecker(String bibtex) {
        int i = 0;
        int j = 0;
        String newScandicBibtex = "";
        for (char c : bibtex.toCharArray()) {
            if (convertScandicLetter(c) != null) {
                newScandicBibtex += bibtex.substring(j, i) + convertScandicLetter(c);
                j = i + 1;
            }
            i++;
        }
        newScandicBibtex += bibtex.substring(j);
        return newScandicBibtex;
    }
    
    private String convertScandicLetter(char c) {
        if (c == 'ö') {
            return "\\\"o";
        } else if (c == 'ä') {
            return "\\\"a";
        } else if (c == 'å') {
            return "\\aa";
        } else if (c == 'Ö') {
            return "\\\"O";
        } else if (c == 'Ä') {
            return "\\\"A";
        } else if (c == 'Å') {
            return "\\AA";
        }
        return null;
    }
}
