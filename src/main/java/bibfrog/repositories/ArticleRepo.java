package bibfrog.repositories;

import bibfrog.domain.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

List<Article> findByAuthors(String author);
List<Article> findByJournal(String journal);
List<Article> findByTitle(String title);
List<Article> findByPublishYear(int year);

}
