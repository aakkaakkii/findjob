package ge.find.findjob.repo;

import ge.find.findjob.domain.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
    List<Organisation> findByUserId(long id);
}
