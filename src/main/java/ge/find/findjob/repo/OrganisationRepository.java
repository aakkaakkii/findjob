package ge.find.findjob.repo;

import ge.find.findjob.domain.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
}
