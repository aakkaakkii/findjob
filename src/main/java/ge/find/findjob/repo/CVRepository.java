package ge.find.findjob.repo;


import ge.find.findjob.domain.CV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CVRepository extends JpaRepository<CV, Long> {
    List<CV> findByUserId(long id);
}
