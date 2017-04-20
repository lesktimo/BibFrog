
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
        
    }
}
