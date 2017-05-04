package bibfrog.service;

import bibfrog.domain.Article;
import bibfrog.domain.Book;
import bibfrog.domain.Inproceeding;
import bibfrog.repositories.ArticleRepo;
import bibfrog.repositories.BooksRepo;
import bibfrog.repositories.InproceedingsRepo;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * Service for returning specified file, creating headers and filepaths.
 */
@Service
public class FileService {
    
    @Autowired
    private InproceedingsRepo iRepo;
    
    @Autowired
    private BooksRepo bRepo;
    
    @Autowired
    private ArticleRepo aRepo;
    
    public Boolean parseACMResponse(InputStream iS) throws FileNotFoundException {
        Scanner s = new Scanner(iS);
        if (!s.hasNext()) {
            return false;
        }
        String rivi = s.nextLine();
        if (rivi.contains("@book")) {
            createBookFromACM(s);
        } else if (rivi.contains("@article")) {
            createArticleFromACM(s);
        } else if (rivi.contains("@inproceedings")) {
            createInproceedingFromACM(s);
            return true;
        }
        return false;
    }
    
    public void createInproceedingFromACM(Scanner s) throws FileNotFoundException {
        Inproceeding inpro = new Inproceeding();
        inpro.setAuthors(parseInfoFromBib(s.nextLine()));
        inpro.setTitle(parseInfoFromBib(s.nextLine()));
        inpro.setBookTitle(parseInfoFromBib(s.nextLine()));
        s.nextLine();
        inpro.setPublishYear(Integer.parseInt(parseInfoFromBib(s.nextLine())));
        inpro.generateReferenceKey();
        iRepo.save(inpro);
    }
    
    public void createBookFromACM(Scanner s) throws FileNotFoundException {
        Book book = new Book();
        book.setAuthors(parseInfoFromBib(s.nextLine()));
        book.setTitle(parseInfoFromBib(s.nextLine()));
        book.setPublishYear(Integer.parseInt(parseInfoFromBib(s.nextLine())));
        s.nextLine();
        book.setPublisher(parseInfoFromBib(s.nextLine()));
        book.generateReferenceKey();
        bRepo.save(book);
    }
    
    public void createArticleFromACM(Scanner s) throws FileNotFoundException {
        Article article = new Article();
        article.setAuthors(parseInfoFromBib(s.nextLine()));
        article.setTitle(parseInfoFromBib(s.nextLine()));
        article.setJournal(parseInfoFromBib(s.nextLine()));
        s.nextLine();
        s.nextLine();
        s.nextLine();
        article.setPublishYear(Integer.parseInt(parseInfoFromBib(s.nextLine())));
        article.generateReferenceKey();
        aRepo.save(article);
    }
    
    public HttpEntity<byte[]> createBibFile(String fileName) throws IOException {
        File referenceFile = getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(createPath(referenceFile));
        return new HttpEntity<>(bytes, createHeaders(referenceFile, fileName));
        
    }

    /**
     * Return file with specified filepath.
     *
     * @param filePath
     * @return specified file
     */
    private File getFilePathForBytes(String filePath) {
        return new File(filePath);
    }

    /**
     * Creates path for given file.
     *
     * @param file
     * @return filepath
     */
    private Path createPath(File file) {
        return Paths.get(file.getPath());
    }

    /**
     * Creates HttpHeaders for given file.
     *
     * @param file
     * @param fileName
     * @return HttpHeaders
     */
    private HttpHeaders createHeaders(File file, String fileName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName + ".bib".replace(".txt", ""));
        headers.setContentLength(file.length());
        return headers;
    }
    
    private String parseInfoFromBib(String bib) {
        String[] halved = bib.split("=");
        String parse = halved[1];
        parse = parse.substring(2, parse.length() - 2);
        return parse;
    }
}
