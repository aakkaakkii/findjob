package ge.find.findjob.services;

import ge.find.findjob.api.OrganisationService;
import ge.find.findjob.domain.Organisation;
import ge.find.findjob.model.OrganisationRequestModel;
import ge.find.findjob.repo.OrganisationRepository;
import ge.find.findjob.repo.UserRepository;
import ge.find.findjob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {
    private final OrganisationRepository organisationRepository;
    private final SecurityUtil securityUtil;
    private final UserRepository userRepository;

    @Override
    public List<Organisation> load() {
        return organisationRepository.findAll();
    }

    @Override
    public List<Organisation> loadCurrentUserExperience() {
        return organisationRepository.findByUserId(securityUtil.getCurrentUserId());
    }

    @Override
    public Organisation get(long id) {
        return organisationRepository.getOne(id);
    }

    @Override
    public Organisation add(OrganisationRequestModel organisationRequestModel) {
        Organisation organisation = Organisation.builder()
                .title(organisationRequestModel.title)
                .address(organisationRequestModel.address)
                .description(organisationRequestModel.description)
                .user(userRepository.getOne(securityUtil.getCurrentUserId()))
                .mail(organisationRequestModel.mail)
                .phone(organisationRequestModel.phone)
                .website(organisationRequestModel.website)
                .blocked(false)
                .build();

        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation update(OrganisationRequestModel organisationRequestModel) {
        Organisation organisation = organisationRepository.getOne(organisationRequestModel.id);

        if (organisation.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        organisation.setTitle(organisationRequestModel.title);
        organisation.setDescription(organisationRequestModel.description);
        organisation.setAddress(organisationRequestModel.address);
        organisation.setMail(organisationRequestModel.mail);
        organisation.setWebsite(organisationRequestModel.website);
        organisation.setPhone(organisationRequestModel.phone);

        return organisationRepository.save(organisation);
    }

    @Override
    public void delete(long id) {
        Organisation organisation = organisationRepository.getOne(id);

        if (organisation.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        organisationRepository.delete(organisation);
    }

    @Override
    public void adminDelete(long id) {
        organisationRepository.deleteById(id);
    }

    @Override
    public Organisation blockOrganisation(long id) {
        Organisation organisation = organisationRepository.getOne(id);
        organisation.setBlocked(true);
        return organisationRepository.save(organisation);
    }

    @Override
    public Organisation unblockOrganisation(long id) {
        Organisation organisation = organisationRepository.getOne(id);
        organisation.setBlocked(false);
        return organisationRepository.save(organisation);
    }
}
