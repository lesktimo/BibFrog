
package bibfrog_JUnit;

import bibfrog.domain.Article;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleTest {
    
    Article article;
    
    @Before
    public void setUp() {
        article = new Article();
    }
    
    @Test
    public void setAuthorsWorksWithMultipleAuthors() {
        article.setGivenAuthors("Kermit The Frog, Saku Sammakko, Pepe The Frog");
        article.setAuthors();
        String[] expected = {"Kermit The Frog", "Saku Sammakko", "Pepe The Frog"};
        assertArrayEquals(expected, article.getAuthors());
    }
    
    @Test
    public void setAuthorsWorksWithOneAuthor() {
        article.setGivenAuthors("Kermit The Frog");
        article.setAuthors();
        String[] expected = {"Kermit The Frog"};
        assertArrayEquals(expected, article.getAuthors());
    }
    
    @Test
    public void authorsStringWorksWithMultipleAuthors() {
        article.setGivenAuthors("Kermit The Frog, Saku Sammakko, Pepe The Frog");
        article.setAuthors();
        String expected = "Kermit The Frog, Saku Sammakko, Pepe The Frog";
        assertEquals(expected, article.authorString());
    }
    
    @Test
    public void authorsStringWorksWithOneAuthor() {
        article.setGivenAuthors("Kermit The Frog");
        article.setAuthors();
        assertEquals("Kermit The Frog", article.authorString());
    }
    
    @Test
    public void optionalFieldsReturnsOptionalValuesInCorrectOrder() {
        article.setVolume(1);
        article.setNumber(1);
        article.setPages("1-2");
        article.setPublishMonth(1);
        article.setNote("Note");
        
        Article expected = new Article();
        
        expected.setVolume(1);
        expected.setNumber(1);
        expected.setPages("1-2");
        expected.setPublishMonth(1);
        expected.setNote("Note");
        
        assertEquals(expected.optionalFields(), article.optionalFields());
    }
    
    @Test
    public void generateReferenceKeyWorks() {
        article.setGivenAuthors("Author");
        article.setAuthors();
        article.setTitle("Title");
        article.setPublishYear(2017);
        article.generateReferenceKey();
        assertTrue(article.getReferenceKey().contains("Ti2017Au"));
        
    }
}
