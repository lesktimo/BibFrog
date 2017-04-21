package bibfrog.domain;

import java.util.LinkedHashMap;

public interface Reference {

    public LinkedHashMap<String, String> optionalFields();

    public String authorString();

    public void setAuthors();
}
