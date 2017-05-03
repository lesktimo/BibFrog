package bibfrog.domain;

import java.util.LinkedHashMap;

/**
 * Reference interface implemented by all models.
 */
public interface Reference {

    /**
     * Generates unique reference key based on required attributes and random
     * number.
     */
    void generateReferenceKey();

    /**
     * Adds all present optional attributes to entity.
     *
     * @return
     */
    LinkedHashMap<String, String> optionalFields();

    /**
     * Return all authors in correct format.
     *
     * @return all authors
     */
    String authorString();

    void setAuthors();
}
