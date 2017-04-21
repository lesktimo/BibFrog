package bibfrog.domain;

import java.util.HashMap;

public interface Reference {

    public HashMap<String, String> optionalFields();

    public String authorString();

    public void setAuthors();
}
