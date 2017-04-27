
package bibfrog_JUnit;

import bibfrog.domain.Book;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    
    Book book;
    
    @Before
    public void setUp() {
        book = new Book();
    }
    
    @Test
    public void setAuthorsWorksWithMultipleAuthors() {
        book.setGivenAuthors("Kermit The Frog, Saku Sammakko, Pepe The Frog");
        book.setAuthors();
        String[] expected = {"Kermit The Frog", "Saku Sammakko", "Pepe The Frog"};
        assertArrayEquals(expected, book.getAuthors());
    }
    
    @Test
    public void setAuthorsWorksWithOneAuthor() {
        book.setGivenAuthors("Kermit The Frog");
        book.setAuthors();
        String[] expected = {"Kermit The Frog"};
        assertArrayEquals(expected, book.getAuthors());
    }
    
    @Test
    public void authorsStringWorksWithMultipleAuthors() {
        book.setGivenAuthors("Kermit The Frog, Saku Sammakko, Pepe The Frog");
        book.setAuthors();
        String expected = "Kermit The Frog, Saku Sammakko, Pepe The Frog";
        assertEquals(expected, book.authorString());
    }
    
    @Test
    public void authorsStringWorksWithOneAuthor() {
        book.setGivenAuthors("Kermit The Frog");
        book.setAuthors();
        assertEquals("Kermit The Frog", book.authorString());
    }
    
    @Test
    public void optionalFieldsReturnsOptionalValuesInCorrectOrder() {
        book.setVolume(1);
        book.setSeries("Series");
        book.setAddress("Address");
        book.setEdition(1);
        book.setPublishMonth(1);
        book.setNote("Note");
        
        Book expected = new Book();
        
        expected.setVolume(1);
        expected.setSeries("Series");
        expected.setAddress("Address");
        expected.setEdition(1);
        expected.setPublishMonth(1);
        expected.setNote("Note");
        
        assertEquals(expected.optionalFields(), book.optionalFields());
    }
    
    @Test
    public void generateReferenceKeyWorks() {
        book.setGivenAuthors("Author");
        book.setAuthors();
        book.setTitle("Title");
        book.setPublishYear(2017);
        book.generateReferenceKey();
        assertTrue(book.getReferenceKey().contains("Ti2017Au"));
        
    }
}
