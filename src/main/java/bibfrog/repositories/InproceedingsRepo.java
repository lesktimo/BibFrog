
package bibfrog.repositories;

import bibfrog.domain.Inproceeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InproceedingsRepo extends JpaRepository<Inproceeding, Long>{
    
}
