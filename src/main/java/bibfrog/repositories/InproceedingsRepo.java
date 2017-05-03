package bibfrog.repositories;

import bibfrog.domain.Inproceeding;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InproceedingsRepo extends JpaRepository<Inproceeding, Long> {

List<Inproceeding> findByAuthors(String author);    
List<Inproceeding> findByBookTitle(String booktitle);
List<Inproceeding> findByTitle(String title);
List<Inproceeding> findByPublishYear(int year);
List<Inproceeding> findByEditor(String editor);
List<Inproceeding> findByPublisher(String publisher);


}
