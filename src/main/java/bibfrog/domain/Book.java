package bibfrog.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Book extends AbstractPersistable<Long> implements Reference {

    //required fields
    private String[] authors;
    @NotNull
    private String title;
    @NotNull
    private String publisher;
    @NotNull
    private int publishYear;
    @NotNull
    private String givenAuthors;
    //optional fields
    private int volume, edition, publishMonth;
    private String series, address, note, referenceKey;

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

    public String getReferenceKey() {
        return referenceKey;
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

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    @Override
    public String toString() {
        return "Book{" + "author=" + author + ", title=" + title + ", publisher=" + publisher + ", publishYear=" + publishYear + ", volume=" + volume + ", edition=" + edition + ", publishMonth=" + publishMonth + ", series=" + series + ", address=" + address + ", note=" + note + '}';
    }

    @Override
    public HashMap<String, String> optionalFields() {
        HashMap<String, String> optionalFields = new HashMap();
        optionalFields.put("volume", "" + volume);
        optionalFields.put("series", series);
        optionalFields.put("address", address);
        optionalFields.put("edition", "" + edition);
        optionalFields.put("publishmonth", publishMonth + "");
        optionalFields.put("note", note);
        return optionalFields;
    }

}
