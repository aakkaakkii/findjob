package ge.find.findjob.repo;

import ge.find.findjob.domain.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByOrganisationId(long id);
    Page<Vacancy> findAllByBlockedIsFalse(Pageable pageable);
}
