package ge.find.findjob.repo;

import ge.find.findjob.domain.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByOrganisationId(long id);
}
