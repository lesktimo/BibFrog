package bibfrog.domain;

import java.util.LinkedHashMap;
import java.util.Random;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Article extends AbstractPersistable<Long> implements Reference {

    //required fields
    private String[] authors;
    @NotNull
    @Length(min = 2, max = 140)
    private String title;
    @NotNull
    @Length(min = 2, max = 140)
    private String journal;
    @NotNull
    private int publishYear;
    @NotNull
    @Length(min = 2, max = 140)
    private String givenAuthors;

    //optional fields
    private String note, pages, referenceKey;
    private int volume, number, publishMonth;

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
    
    public String[] getAuthors() {
        return authors;
    }

    @Override
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

    @Override
    public String authorString() {
        String printBuilder = "";
        for (String author : authors) {
            printBuilder += author + ", ";
        }
        return printBuilder.substring(0, printBuilder.length() - 2);
    }

    @Override
    public LinkedHashMap<String, String> optionalFields() {
        LinkedHashMap<String, String> optionalFields = new LinkedHashMap();
        optionalFields.put("volume", "" + volume);
        optionalFields.put("number", "" + number);
        optionalFields.put("pages", pages);
        optionalFields.put("month", publishMonth + "");
        optionalFields.put("note", note);
        return optionalFields;
    }
    
    @Override
    public void generateReferenceKey() {
        this.referenceKey = title.substring(0, 2).trim() + this.publishYear + this.authorString().substring(0, 2).trim() + super.getId() + new Random().nextInt(1000);
    }
}
