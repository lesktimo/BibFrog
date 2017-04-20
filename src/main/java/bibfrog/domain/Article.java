package bibfrog.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.validation.constraints.NotNull;

@Entity
public class Article extends AbstractPersistable<Long> implements Reference {

    @NotNull
    private String author, title, journal;
    
    @NotNull
    private int publishYear;
    
    //optional fields
    private String note, pages, referenceKey, series;
    private int volume, number, publishMonth;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getPublishMonth() {
        return publishMonth;
    }

    public void setPublishMonth(int publishMonth) {
        this.publishMonth = publishMonth;
    }

    
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String article) {
        this.author = article;
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
