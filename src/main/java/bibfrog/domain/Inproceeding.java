package bibfrog.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Inproceeding extends AbstractPersistable<Long> implements Reference {

    //required fields
    private String[] authors;
    @NotNull
    private String title;
    @NotNull
    private String bookTitle;
    @NotNull
    private int publishYear;
    private String referenceKey;
    //optional fields
    private String editor, address, organization, publisher, note;
    private int volume, startPage, endPage, publishMonth;

    //helper building the array
    @NotNull
    private String givenAuthors;

    public HashMap<String, String> optionalFields() {

        HashMap<String, String> optionalFields = new HashMap();
        optionalFields.put("editor", editor);
        optionalFields.put("address", address);
        optionalFields.put("organization", organization);
        optionalFields.put("publisher", publisher);
        optionalFields.put("note", note);
        optionalFields.put("volume", volume + "");
        optionalFields.put("startpage", startPage + "");
        optionalFields.put("endpage", endPage + "");
        optionalFields.put("publishmonth", publishMonth + "");

        return optionalFields;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
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

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors() {
        if (this.givenAuthors.contains(",")) {
            this.authors = this.givenAuthors.trim().split(",");
        } else {
            String[] helper = {""};
            helper[0] = this.givenAuthors;
            this.authors = helper;
        }
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

    public String authorString() {
        String printBuilder = "";
        for (String author : authors) {
            printBuilder += author + ", ";
        }
        return printBuilder.substring(0, printBuilder.length() - 2);
    }

    @Override
    public String toString() {
        return "Inproceeding{" + "authors= [" + this.authorString() + "] , title=" + title + ", bookTitle=" + bookTitle + ", year=" + publishYear + ", editor=" + editor + ", address=" + address + ", organization=" + organization + ", publisher=" + publisher + ", note=" + note + ", volume=" + volume + ", startPage=" + startPage + ", endPage=" + endPage + ", publishMonth=" + publishMonth + '}';
    }

}
