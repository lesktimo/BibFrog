package bibfrog.repositories;

import bibfrog.domain.Article;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

    Collection<Article> findByTitleContainingIgnoringCase(String title);

    Collection<Article> findByJournalContainingIgnoringCase(String journal);

    Collection<Article> findByAuthorsContainingIgnoringCase(String author);

    Collection<Article> findByPublishYear(Integer year);

}
