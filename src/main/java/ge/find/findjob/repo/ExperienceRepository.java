package ge.find.findjob.repo;

import ge.find.findjob.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByUserId(long id);
    @Query(
            value = "SELECT e from Experience e WHERE e.id IN :ids"
    )
    List<Experience> findByIds(@Param("ids") List<Long> ids);

}
