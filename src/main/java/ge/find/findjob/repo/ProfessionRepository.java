package ge.find.findjob.repo;

import ge.find.findjob.domain.ProfessionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionRepository  extends JpaRepository<ProfessionTag, Long> {
    ProfessionTag findByTitle(String title);
    @Query(
            value = "SELECT p from ProfessionTag p WHERE p.id IN :ids"
    )
    List<ProfessionTag> findByIds(@Param("ids") List<Long> ids);
}
