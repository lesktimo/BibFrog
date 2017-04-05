package bibfrog.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

public abstract class Reference extends AbstractPersistable<Long>{

    private String[] authors;
    private String title;
    private int year;
    private String citation_key;

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCitation_key() {
        return citation_key;
    }

    public void setCitation_key(String citation_key) {
        this.citation_key = citation_key;
    }



}
