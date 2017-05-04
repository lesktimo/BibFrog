package bibfrog.repositories;

import bibfrog.domain.Book;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Book, Long> {

    Collection<Book> findByAuthorsContainingIgnoringCase(String author);

    Collection<Book> findByTitleContainingIgnoringCase(String title);

    Collection<Book> findByPublisherContainingIgnoringCase(String publisher);

    Collection<Book> findByPublishYear(Integer year);

}
