package ge.find.findjob.api;

import ge.find.findjob.domain.Organisation;
import ge.find.findjob.model.OrganisationRequestModel;

import java.util.List;

public interface OrganisationService {
    List<Organisation> load();
    Organisation get(long id);
    Organisation add(OrganisationRequestModel organisationRequestModel);
    Organisation update(OrganisationRequestModel organisationRequestModel);
    void delete(long id);
    void adminDelete(long id);
    Organisation blockOrganisation(long id);
    Organisation unblockOrganisation(long id);
}
