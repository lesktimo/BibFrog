package bibfrog_JUnit;

import bibfrog.domain.*;
import bibfrog.service.ExportService;
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
        Inproceeding inpro = new Inproceeding();
        inpro.setReferenceKey("KEY");
        inpro.setGivenAuthors("Author");
        inpro.setAuthors();
        inpro.setTitle("Title");
        inpro.setBookTitle("Booktitle");
        inpro.setPublishYear(2017);
        System.out.println(es.createBibtexFromInproceeding(inpro));
        String expected = "@inproceedings{KEY,\n"
                + "author = {Author},\n"
                + "title = {Title},\n"
                + "booktitle = {Booktitle},\n"
                + "year = {2017}\n"
                + "}";
        assertEquals(expected, es.createBibtexFromInproceeding(inpro));
    }
}
