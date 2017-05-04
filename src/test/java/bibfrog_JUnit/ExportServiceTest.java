package bibfrog_JUnit;

import bibfrog.domain.*;
import bibfrog.service.ExportService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExportServiceTest {

    ExportService es;

    @Before
    public void setUp() {
        es = new ExportService();
    }

    @Test
    public void createBibtexFromInproWorks() {
        Inproceeding inpro = setInproceeding();
        String expected = "@inproceedings{KEY,\n"
                + "author = {Author},\n"
                + "title = {Title},\n"
                + "booktitle = {Booktitle},\n"
                + "year = {2017}\n"
                + "}";
        assertEquals(expected, es.createBibtexFromInproceeding(inpro));
    }

    @Test
    public void createBibtexFromBookWorks() {
        Book book = setBook();
        String expected = "@book{KEY,\n"
                + "author = {Author},\n"
                + "title = {Title},\n"
                + "publisher = {Publisher},\n"
                + "year = {2017}\n"
                + "}";
        assertEquals(expected, es.createBibtexFromBook(book));
    }

    @Test
    public void createBibtexFromArticleWorks() {
        Article article = setArticle();
        String expected = "@article{KEY,\n"
                + "author = {Author},\n"
                + "title = {Title},\n"
                + "journal = {Journal},\n"
                + "year = {2017}\n"
                + "}";
        assertEquals(expected, es.createBibtexFromArticle(article));
    }

    @Test
    public void optionalFieldsAreAddedCorrectly() {
        Article article = setArticle();
        article.setPages("1--2");
        article.setPublishMonth(8);

        String expected = "@article{KEY,\n"
                + "author = {Author},\n"
                + "title = {Title},\n"
                + "journal = {Journal},\n"
                + "year = {2017},\n"
                + "pages = {1--2},\n"
                + "month = {8}\n"
                + "}";
        assertEquals(expected, es.createBibtexFromArticle(article));
    }

    @Test
    public void everyOptionalFieldIsAddedCorrectly() {
        Article article = setArticle();
        article.setVolume(1);
        article.setNumber(2);
        article.setPages("1--2");
        article.setPublishMonth(8);
        article.setNote("Note");

        String expected = "@article{KEY,\n"
                + "author = {Author},\n"
                + "title = {Title},\n"
                + "journal = {Journal},\n"
                + "year = {2017},\n"
                + "volume = {1},\n"
                + "number = {2},\n"
                + "pages = {1--2},\n"
                + "month = {8},\n"
                + "note = {Note}\n"
                + "}";
        assertEquals(expected, es.createBibtexFromArticle(article));
    }

    @Test
    public void bibtexIsCreatedCorrectlyFromEveryArticle() {
        Article article = setArticle();

        List<Article> articles = new ArrayList<>();
        articles.add(article);

        String bibtex = es.createBibtexFromAllArticles(articles);
        String expected = es.createBibtexFromArticle(article);

        assertEquals(expected, bibtex);
    }

    @Test
    public void bibtexIsCreatedCorrectlyFromEveryBook() {
        Book book = setBook();

        List<Book> books = new ArrayList<>();
        books.add(book);

        String bibtex = es.createBibtexFromAllBooks(books);
        String expected = es.createBibtexFromBook(book);

        assertEquals(expected, bibtex);
    }

    @Test
    public void bibtexIsCreatedCorrectlyFromEveryInproceeding() {
        Inproceeding inpro1 = setInproceeding();
        Inproceeding inpro2 = new Inproceeding();
        inpro2.setReferenceKey("KEY2");
        inpro2.setAuthors("Author2");
        inpro2.setTitle("Title2");
        inpro2.setBookTitle("Booktitle2");
        inpro2.setPublishYear(2017);

        List<Inproceeding> inpros = new ArrayList<>();
        inpros.add(inpro1);
        inpros.add(inpro2);

        String bibtex = es.createBibtexFromAllInproceedings(inpros);
        String expected = es.createBibtexFromInproceeding(inpro1) + "\n\n"
                + es.createBibtexFromInproceeding(inpro2);

        assertEquals(expected, bibtex);
    }

    @Test
    public void bibtexIsCreatedCorrectlyFromEveryReference() {
        Article article = setArticle();
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        
        Book book = setBook();
        List<Book> books = new ArrayList<>();
        books.add(book);
        
        Inproceeding inpro = setInproceeding();
        List<Inproceeding> inpros = new ArrayList<>();
        inpros.add(inpro);
        
        String bibtex = es.createBibtexFromAll(inpros, books, articles);
        
        String expected = es.createBibtexFromAllInproceedings(inpros) +
                es.createBibtexFromAllBooks(books) + 
                es.createBibtexFromAllArticles(articles);
        
        assertEquals(expected, bibtex);
    }
    
    @Test
    public void bibtexIsCreatedCorrectlyFromEveryArticleAndBook() {
        Article article = setArticle();
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        
        Book book = setBook();
        List<Book> books = new ArrayList<>();
        books.add(book);
        
        List<Inproceeding> inpros = new ArrayList<>();
        
        String bibtex = es.createBibtexFromAll(inpros, books, articles);
        
        String expected = es.createBibtexFromAllBooks(books) + 
                es.createBibtexFromAllArticles(articles);
        
        assertEquals(expected, bibtex);
    }

    @Test
    public void scandicCheckerDoesntChangeIfNoScandics() {
        assertEquals("Some string", es.scandicChecker("Some string"));
    }

    @Test
    public void scandicCheckerChangesSmallScandics() {
        assertEquals("String c\\\"ont\\\"aining \\aa", es.scandicChecker("String cöntäining å"));
    }

    @Test
    public void scandicCheckerChangesCapitalScandics() {
        assertEquals("String c\\\"Ont\\\"Aining \\AA", es.scandicChecker("String cÖntÄining Å"));
    }

    private Article setArticle() {
        Article article = new Article();
        article.setReferenceKey("KEY");
        article.setAuthors("Author");
        article.setTitle("Title");
        article.setJournal("Journal");
        article.setPublishYear(2017);

        return article;
    }

    private Inproceeding setInproceeding() {
        Inproceeding inpro = new Inproceeding();
        inpro.setReferenceKey("KEY");
        inpro.setAuthors("Author");
        inpro.setTitle("Title");
        inpro.setBookTitle("Booktitle");
        inpro.setPublishYear(2017);

        return inpro;
    }

    private Book setBook() {
        Book book = new Book();
        book.setReferenceKey("KEY");
        book.setAuthors("Author");
        book.setTitle("Title");
        book.setPublisher("Publisher");
        book.setPublishYear(2017);

        return book;
    }
}
