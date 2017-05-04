
package bibfrog_JUnit;

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
    public void optionalFieldsReturnsOptionalValuesInCorrectOrder() {
        inpro.setEditor("test1");
        inpro.setVolume(1);
        inpro.setSeries("test2");
        inpro.setPages("test3");
        inpro.setAddress("test4");
        inpro.setPublishMonth(12);
        inpro.setOrganization("test5");
        inpro.setPublisher("test6");
        inpro.setNote("test7");
        
        Inproceeding expected = new Inproceeding();
        
        expected.setEditor("test1");
        expected.setVolume(1);
        expected.setSeries("test2");
        expected.setPages("test3");
        expected.setAddress("test4");
        expected.setPublishMonth(12);
        expected.setOrganization("test5");
        expected.setPublisher("test6");
        expected.setNote("test7");
        
        assertEquals(expected.optionalFields(), inpro.optionalFields());
    }
    
    @Test
    public void generateReferenceKeyWorks() {
        inpro.setAuthors("Author");
        inpro.setTitle("Title");
        inpro.setPublishYear(2017);
        inpro.generateReferenceKey();
        assertTrue(inpro.getReferenceKey().contains("Ti2017Au"));
        
    }
}
