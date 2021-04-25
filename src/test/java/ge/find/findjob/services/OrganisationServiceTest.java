package ge.find.findjob.services;

import ge.find.findjob.api.OrganisationService;
import ge.find.findjob.domain.Organisation;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.OrganisationRequestModel;
import ge.find.findjob.repo.OrganisationRepository;
import ge.find.findjob.repo.UserRepository;
import ge.find.findjob.repo.VacancyRepository;
import ge.find.findjob.util.SecurityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cache.CacheManager;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class OrganisationServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityUtil securityUtil;
    @Mock
    private OrganisationRepository organisationRepository;
    @Mock
    private CacheManager cacheManager;
    @Mock
    private VacancyRepository vacancyRepository;
    private OrganisationService organisationService;

    @Before
    public void init() {
        organisationService = new OrganisationServiceImpl(organisationRepository, securityUtil, userRepository, vacancyRepository,  cacheManager);

        Mockito.when(organisationRepository.save(Mockito.any(Organisation.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void addTest() {
        User user = createUser();
        OrganisationRequestModel organisationRequest = createOrganisationRequest();
        Organisation organisation = createOrganisation(organisationRequest);

        Assertions.assertEquals(organisation.getTitle(), organisationRequest.title);
        Assertions.assertEquals(organisation.getDescription(), organisationRequest.description);
        Assertions.assertEquals(organisation.getAddress(), organisationRequest.address);
        Assertions.assertEquals(organisation.getPhone(), organisationRequest.phone);
        Assertions.assertEquals(organisation.getMail(), organisationRequest.mail);
        Assertions.assertEquals(organisation.getWebsite(), organisationRequest.website);
        Assertions.assertFalse(organisation.isBlocked());
        Assertions.assertEquals(organisation.getUser().getId(), user.getId());
        Assertions.assertEquals(organisation.getUser().getUsername(), user.getUsername());
    }

    @Test
    public void updateTest() {
        User user = createUser();
        OrganisationRequestModel oldOrganisationRequest = createOrganisationRequest();
        Organisation organisation = createOrganisation(oldOrganisationRequest);
        OrganisationRequestModel organisationRequest = createOrganisationRequest();

        Mockito.when(organisationRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> organisation);

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId());

        Organisation updated = organisationService.update(organisationRequest);

        Assertions.assertEquals(updated.getTitle(), organisationRequest.title);
        Assertions.assertEquals(updated.getDescription(), organisationRequest.description);
        Assertions.assertEquals(updated.getAddress(), organisationRequest.address);
        Assertions.assertEquals(updated.getPhone(), organisationRequest.phone);
        Assertions.assertEquals(updated.getMail(), organisationRequest.mail);
        Assertions.assertEquals(updated.getWebsite(), organisationRequest.website);
        Assertions.assertFalse(organisation.isBlocked());
        Assertions.assertEquals(updated.getUser().getId(), user.getId());
        Assertions.assertEquals(updated.getUser().getUsername(), user.getUsername());

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId() + 1);

        Exception differentUser = Assertions.assertThrows(RuntimeException.class, () -> {
            organisationService.update(organisationRequest);
        });

        Mockito.when(organisationRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> null);

        Exception notFound = Assertions.assertThrows(RuntimeException.class, () -> {
            organisationService.update(organisationRequest);
        });

    }

    @Test
    public void blockUnblockTest() {
        OrganisationRequestModel oldOrganisationRequest = createOrganisationRequest();
        Organisation organisation = createOrganisation(oldOrganisationRequest);

        Mockito.when(organisationRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> organisation);

        Assertions.assertFalse(organisation.isBlocked());

        organisationService.blockOrganisation(organisation.getId());
        Assertions.assertTrue(organisation.isBlocked());

        organisationService.unblockOrganisation(organisation.getId());
        Assertions.assertFalse(organisation.isBlocked());

        organisationService.blockOrganisation(organisation.getId());
        Assertions.assertTrue(organisation.isBlocked());
    }

    private User createUser() {
        return User.builder()
                .id(1)
                .username("admin")
                .build();
    }

    private Organisation createOrganisation(OrganisationRequestModel organisationRequest) {
        Mockito.when(userRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> createUser());

        return organisationService.add(organisationRequest);
    }

    private OrganisationRequestModel createOrganisationRequest() {
        OrganisationRequestModel organisationRequest = new OrganisationRequestModel();
        organisationRequest.title = UUID.randomUUID().toString();
        organisationRequest.description = UUID.randomUUID().toString();
        organisationRequest.address = UUID.randomUUID().toString();
        organisationRequest.phone = UUID.randomUUID().toString();
        organisationRequest.website = UUID.randomUUID().toString();
        organisationRequest.mail = UUID.randomUUID().toString();

        return organisationRequest;
    }
}
