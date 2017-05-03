package bibfrog.repositories;

import bibfrog.domain.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Book, Long> {

List<Book> findByAuthors(String author);
List<Book> findByTitle(String title);
List<Book> findByPublisher(String publisher);
List<Book> findByPublishYear(int year);


}
