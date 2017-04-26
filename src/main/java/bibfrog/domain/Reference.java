package bibfrog.domain;

import java.util.LinkedHashMap;

public interface Reference {
    
    void generateReferenceKey();
    
    LinkedHashMap<String, String> optionalFields();

    String authorString();

    void setAuthors();
}
