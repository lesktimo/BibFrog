package bibfrog.domain;

import java.util.HashMap;
<<<<<<< HEAD
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Article extends AbstractPersistable<Long> implements Reference {
=======
import javax.validation.constraints.NotNull;

public class Article implements Reference {
    
    
//    @article{article,
//  author  = {Peter Adams}, 
//  title   = {The title of the work},
//  journal = {The name of the journal},
//  year    = 1993,
//  number  = 2,
//  pages   = {201-213},
//  month   = 7,
//  note    = {An optional note}, 
//  volume  = 4
//}
    
    @NotNull
    private String article, title, journal;
    
    @NotNull
    private int year;
    
    //optional fields
    private String note, pages, referenceKey;
    private int volume, number, month;
>>>>>>> 0d0e56eb41d4dc0e0466a682b5362ad4195f2f4a

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    
    
    @Override
    public HashMap<String, String> optionalFields() {
        HashMap<String, String> optionalFields = new HashMap();
        optionalFields.put("volume", "" + volume);
        optionalFields.put("number", "" + number);
        optionalFields.put("pages", pages);
        optionalFields.put("month", month + "");
        optionalFields.put("note", note);
        

        return optionalFields;
    }

}
