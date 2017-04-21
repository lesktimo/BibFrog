package bibfrog.domain;

import java.util.LinkedHashMap;

public interface Reference {
    
    public void generateReferenceKey();
    
    public LinkedHashMap<String, String> optionalFields();

    public String authorString();

    public void setAuthors();
}
