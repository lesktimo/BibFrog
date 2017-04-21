package bibfrog.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.validation.constraints.NotNull;

@Entity
public class Article extends AbstractPersistable<Long> implements Reference {

    //required fields
    private String[] authors;
    @NotNull
    private String title;
    @NotNull
    private String journal;
    @NotNull
    private int publishYear;
    @NotNull
    private String givenAuthors;

    //optional fields
    private String note, pages, referenceKey;
    private int volume, number, publishMonth;

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors() {
        if (this.givenAuthors.contains(",")) {
            this.authors = this.givenAuthors.trim().split(",");
            for (int i = 0; i < authors.length; i++) {
                authors[i] = authors[i].trim();
            }
        } else {
            String[] helper = {""};
            helper[0] = this.givenAuthors.trim();
            this.authors = helper;
        }
    }

    public String getGivenAuthors() {
        return givenAuthors;
    }

    public void setGivenAuthors(String givenAuthors) {
        this.givenAuthors = givenAuthors;
    }

    public int getPublishMonth() {
        return publishMonth;
    }

    public void setPublishMonth(int publishMonth) {
        this.publishMonth = publishMonth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int year) {
        this.publishYear = year;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String authorString() {
        String printBuilder = "";
        for (String author : authors) {
            printBuilder += author + ", ";
        }
        return printBuilder.substring(0, printBuilder.length() - 2);
    }

    @Override
    public HashMap<String, String> optionalFields() {
        HashMap<String, String> optionalFields = new HashMap();
        optionalFields.put("volume", "" + volume);
        optionalFields.put("number", "" + number);
        optionalFields.put("pages", pages);
        optionalFields.put("month", publishMonth + "");
        optionalFields.put("note", note);
        return optionalFields;
    }
}
