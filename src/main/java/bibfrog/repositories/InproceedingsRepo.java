package bibfrog.repositories;

import bibfrog.domain.Inproceeding;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InproceedingsRepo extends JpaRepository<Inproceeding, Long> {

    Collection<Inproceeding> findByAuthorsContainingIgnoringCase(String author);

    Collection<Inproceeding> findByBookTitleContainingIgnoringCase(String booktitle);

    Collection<Inproceeding> findByTitleContainingIgnoringCase(String title);

    Collection<Inproceeding> findByPublishYear(Integer year);

    Collection<Inproceeding> findByEditorContainingIgnoringCase(String editor);

    Collection<Inproceeding> findByPublisherContainingIgnoringCase(String publisher);

}
