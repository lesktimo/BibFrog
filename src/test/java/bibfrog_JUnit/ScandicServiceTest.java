
package bibfrog_JUnit;

import bibfrog.service.ScandicService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScandicServiceTest {
    
    ScandicService sc;
    
    @Before
    public void setUp() {
        sc = new ScandicService();
    }
    
    @Test
    public void scandicCheckerDoesntChangeIfNoScandics() {
        assertEquals("Some string", sc.scandicChecker("Some string"));
    }
    
    @Test
    public void scandicCheckerChangesSmallScandics() {
        assertEquals("String c\\\"ont\\\"aining \\aa", sc.scandicChecker("String cöntäining å"));
    }
    
    @Test
    public void scandicCheckerChangesCapitalScandics() {
        assertEquals("String c\\\"Ont\\\"Aining \\AA", sc.scandicChecker("String cÖntÄining Å"));
    }
}
