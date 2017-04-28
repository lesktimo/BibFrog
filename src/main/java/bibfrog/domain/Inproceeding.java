package bibfrog.domain;

import java.util.LinkedHashMap;
import java.util.Random;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Inproceeding extends AbstractPersistable<Long> implements Reference {

    //required fields
    private String[] authors;
    @NotNull
    @Length(min = 2, max = 140)
    private String title;
    @NotNull
    @Length(min = 2, max = 140)
    private String bookTitle;
    @NotNull
    private int publishYear;
    //helper building the array
    @NotNull
    @Length(min = 2, max = 140)
    private String givenAuthors;

    private String referenceKey;
    //optional fields
    private String editor, address, pages, organization, publisher, series, note;
    private int volume, publishMonth;

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getMonth() {
        return publishMonth;
    }

    public void setMonth(int month) {
        this.publishMonth = month;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return publishYear;
    }

    public void setYear(int year) {
        this.publishYear = year;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPublishMonth() {
        return publishMonth;
    }

    public void setPublishMonth(int publishMonth) {
        this.publishMonth = publishMonth;
    }

    public String getGivenAuthors() {
        return givenAuthors;
    }

    public void setGivenAuthors(String givenAuthors) {
        this.givenAuthors = givenAuthors;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
    
    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    @Override
    public String authorString() {
        String printBuilder = "";
        for (String author : authors) {
            printBuilder += author + ", ";
        }
        return printBuilder.substring(0, printBuilder.length() - 2);
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
    public LinkedHashMap<String, String> optionalFields() {
        LinkedHashMap<String, String> optionalFields = new LinkedHashMap();
        optionalFields.put("editor", editor);
        optionalFields.put("volume", volume + "");
        optionalFields.put("series", series);
        optionalFields.put("pages", pages);
        optionalFields.put("address", address);
        optionalFields.put("publishmonth", publishMonth + "");
        optionalFields.put("organization", organization);
        optionalFields.put("publisher", publisher);
        optionalFields.put("note", note);
        return optionalFields;
    }

    @Override
    public void generateReferenceKey() {
        this.referenceKey = title.substring(0, 2).trim() + this.publishYear + this.authorString().substring(0, 2).trim() + super.getId() + new Random().nextInt(1000);
    }
}
