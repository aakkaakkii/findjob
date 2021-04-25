package ge.find.findjob.repo;

import ge.find.findjob.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByUserId(long id);
    @Query(
            value = "SELECT e from Education e WHERE e.id IN :ids"
    )
    List<Education> findByIds(@Param("ids") List<Long> ids);
}
