package ge.find.findjob.services;

import ge.find.findjob.api.VacancyService;
import ge.find.findjob.domain.*;
import ge.find.findjob.model.CVRequestModel;
import ge.find.findjob.model.OrganisationRequestModel;
import ge.find.findjob.model.VacancyRequestModel;
import ge.find.findjob.repo.OrganisationRepository;
import ge.find.findjob.repo.ProfessionRepository;
import ge.find.findjob.repo.VacancyRepository;
import ge.find.findjob.util.SecurityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class VacancyServiceTest {
    @Mock
    private SecurityUtil securityUtil;
    @Mock
    private VacancyRepository vacancyRepository;
    @Mock
    private ProfessionRepository professionRepository;
    @Mock
    private OrganisationRepository organisationRepository;
    private VacancyService vacancyService;

    @Before
    public void init() {
        vacancyService = new VacancyServiceImpl(vacancyRepository, professionRepository, organisationRepository, securityUtil);

        Mockito.when(vacancyRepository.save(Mockito.any(Vacancy.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Mockito.when(professionRepository.findByIds(Mockito.<Long>anyList()))
                .thenAnswer(i -> ((List<Long>) i.getArguments()[0]).stream()
                        .map(d -> new ProfessionTag(d, d.toString()))
                        .collect(Collectors.toList()));
    }

    @Test
    public void addTest() {
        Organisation organisation = createOrganisation();
        VacancyRequestModel vacancyRequest = createVacancyRequest();
        Vacancy vacancy = createVacancy(vacancyRequest);

        Assertions.assertEquals(vacancy.getTitle(), vacancyRequest.title);
        Assertions.assertEquals(vacancy.getDescription(), vacancyRequest.description);
        Assertions.assertEquals(vacancy.getSalary(), vacancyRequest.salary);
        Assertions.assertEquals(vacancy.getVacancyType(), vacancyRequest.vacancyType);
        Assertions.assertEquals(vacancy.getProfessionTags().get(1).getId(), vacancyRequest.professionTags.get(1));
        Assertions.assertFalse(vacancy.isBlocked());
        Assertions.assertNotNull(vacancy.getOrganisation());
        Assertions.assertEquals(vacancy.getOrganisation().getId(), organisation.getId());
        Assertions.assertEquals(vacancy.getOrganisation().getTitle(), organisation.getTitle());
    }

    @Test
    public void updateTest() {
        User user = createUser();
        Organisation organisation = createOrganisation();
        VacancyRequestModel oldVacancyRequest = createVacancyRequest();
        Vacancy vacancy = createVacancy(oldVacancyRequest);
        VacancyRequestModel vacancyRequest = createVacancyRequest();

        Mockito.when(vacancyRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> vacancy);

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId());

        Vacancy updated = vacancyService.update(vacancyRequest);

        Assertions.assertEquals(updated.getTitle(), vacancyRequest.title);
        Assertions.assertEquals(updated.getDescription(), vacancyRequest.description);
        Assertions.assertEquals(updated.getSalary(), vacancyRequest.salary);
        Assertions.assertEquals(updated.getVacancyType(), vacancyRequest.vacancyType);
        Assertions.assertEquals(updated.getProfessionTags().get(1).getId(), vacancyRequest.professionTags.get(1));
        Assertions.assertFalse(updated.isBlocked());
        Assertions.assertNotNull(updated.getOrganisation());
        Assertions.assertEquals(updated.getOrganisation().getId(), organisation.getId());
        Assertions.assertEquals(updated.getOrganisation().getTitle(), organisation.getTitle());

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId() + 1);

        Exception differentUser = Assertions.assertThrows(RuntimeException.class, () -> {
            vacancyService.update(vacancyRequest);
        });

        Mockito.when(vacancyRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> null);

        Exception notFound = Assertions.assertThrows(RuntimeException.class, () -> {
            vacancyService.update(vacancyRequest);
        });
    }

    @Test
    public void blockUnblockVacancyTest() {
        VacancyRequestModel oldVacancyRequest = createVacancyRequest();
        Vacancy vacancy = createVacancy(oldVacancyRequest);

        Mockito.when(vacancyRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> vacancy);

        Assertions.assertFalse(vacancy.isBlocked());

        vacancyService.blockVacancy(vacancy.getId());
        Assertions.assertTrue(vacancy.isBlocked());

        vacancyService.unblockVacancy(vacancy.getId());
        Assertions.assertFalse(vacancy.isBlocked());

        vacancyService.blockVacancy(vacancy.getId());
        Assertions.assertTrue(vacancy.isBlocked());
    }

    private Organisation createOrganisation() {
        return Organisation.builder()
                .id(1)
                .title("organisation")
                .user(createUser())
                .build();
    }

    private User createUser() {
        return User.builder()
                .id(1)
                .username("admin")
                .build();
    }

    private Vacancy createVacancy(VacancyRequestModel vacancyRequest) {
        Mockito.when(organisationRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> createOrganisation());

        return vacancyService.add(vacancyRequest);
    }

    private VacancyRequestModel createVacancyRequest(){
        VacancyRequestModel vacancyRequestModel = new VacancyRequestModel();
        vacancyRequestModel.title = UUID.randomUUID().toString();
        vacancyRequestModel.description = UUID.randomUUID().toString();
        vacancyRequestModel.salary = new Random().nextDouble();
        vacancyRequestModel.vacancyType = VacancyType.values()[new Random().nextInt(3)];
        vacancyRequestModel.professionTags = Arrays.asList(new Random().nextLong(), new Random().nextLong());
        vacancyRequestModel.organisationId = createOrganisation().getId();

        return vacancyRequestModel;
    }

}
