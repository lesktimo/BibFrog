package bibfrog.domain;

import java.util.LinkedHashMap;
import java.util.Random;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Book model.
 */
@Entity
public class Book extends AbstractPersistable<Long> implements Reference {

    //required fields
    @NotNull
    @Length(min = 2, max = 140)
    private String title;
    @NotNull
    @Length(min = 2, max = 140)
    private String publisher;
    @NotNull
    private int publishYear;
    @NotNull
    @Length(min = 2, max = 140)
    private String authors;
    //optional fields
    private int volume, edition, publishMonth;
    private String series, address, note, referenceKey;

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getPublishMonth() {
        return publishMonth;
    }

    public void setPublishMonth(int publishMonth) {
        this.publishMonth = publishMonth;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    @Override
    public LinkedHashMap<String, String> optionalFields() {
        LinkedHashMap<String, String> optionalFields = new LinkedHashMap();
        optionalFields.put("volume", "" + volume);
        optionalFields.put("series", series);
        optionalFields.put("address", address);
        optionalFields.put("edition", "" + edition);
        optionalFields.put("publishmonth", publishMonth + "");
        optionalFields.put("note", note);
        return optionalFields;
    }

    @Override
    public void generateReferenceKey() {
        this.referenceKey = title.substring(0, 2).trim() + this.publishYear + this.authors.substring(0, 2).trim() + super.getId() + new Random().nextInt(1000);
    }
}
