package bibfrog.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Book extends AbstractPersistable<Long> implements Reference {

    //required fields
    @NotNull
    private String author, title, publisher;
    @NotNull
    private int publishYear;
    //optional fields
    private int volume, edition, publishMonth;
    private String series, address, note;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "Book{" + "author=" + author + ", title=" + title + ", publisher=" + publisher + ", publishYear=" + publishYear + ", volume=" + volume + ", edition=" + edition + ", publishMonth=" + publishMonth + ", series=" + series + ", address=" + address + ", note=" + note + '}';
    }

    @Override
    public HashMap<String, String> optionalFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
