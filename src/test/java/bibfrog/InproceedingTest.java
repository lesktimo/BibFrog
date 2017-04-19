
package bibfrog;

import bibfrog.domain.Inproceeding;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InproceedingTest {
    
    Inproceeding inpro;
    
    @Before
    public void setUp() {
        inpro = new Inproceeding();
    }
    
    @Test
    public void setAuthorsWorksWithMultipleAuthors() {
        inpro.setGivenAuthors("Kermit The Frog, Saku Sammakko, Pepe The Frog");
        inpro.setAuthors();
        String[] expected = {"Kermit The Frog", "Saku Sammakko", "Pepe The Frog"};
        assertArrayEquals(expected, inpro.getAuthors());
    }
    
    @Test
    public void setAuthorsWorksWithOneAuthor() {
        inpro.setGivenAuthors("Kermit The Frog");
        inpro.setAuthors();
        String[] expected = {"Kermit The Frog"};
        assertArrayEquals(expected, inpro.getAuthors());
    }
    
    @Test
    public void authorsStringWorksWithMultipleAuthors() {
        inpro.setGivenAuthors("Kermit The Frog, Saku Sammakko, Pepe The Frog");
        inpro.setAuthors();
        String expected = "Kermit The Frog, Saku Sammakko, Pepe The Frog";
        assertEquals(expected, inpro.authorString());
    }
    
    @Test
    public void authorsStringWorksWithOneAuthor() {
        inpro.setGivenAuthors("Kermit The Frog");
        inpro.setAuthors();
        assertEquals("Kermit The Frog", inpro.authorString());
    }
}
