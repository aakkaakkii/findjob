package ge.find.findjob.services;

import ge.find.findjob.api.CVService;
import ge.find.findjob.domain.*;
import ge.find.findjob.model.CVRequestModel;
import ge.find.findjob.repo.*;
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
public class CVServiceTest {
    @Mock
    private CVRepository cvRepository;
    @Mock
    private SecurityUtil securityUtil;
    @Mock
    private ProfessionRepository professionRepository;
    @Mock
    private ExperienceRepository experienceRepository;
    @Mock
    private EducationRepository educationRepository;
    @Mock
    private UserRepository userRepository;
    private CVService cvService;

    @Before
    public void init() {
        cvService = new CVServiceImpl(cvRepository, securityUtil, professionRepository, experienceRepository, educationRepository, userRepository);

        Mockito.when(cvRepository.save(Mockito.any(CV.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Mockito.when(professionRepository.findByIds(Mockito.<Long>anyList()))
                .thenAnswer(i -> ((List<Long>) i.getArguments()[0]).stream()
                        .map(d -> new ProfessionTag(d, d.toString()))
                        .collect(Collectors.toList()));

        Mockito.when(educationRepository.findByIds(Mockito.<Long>anyList()))
                .thenAnswer(i -> ((List<Long>) i.getArguments()[0]).stream()
                        .map(d -> new Education(d, d.toString(), d.toString(), new Date(), new Date(), null))
                        .collect(Collectors.toList()));

        Mockito.when(experienceRepository.findByIds(Mockito.<Long>anyList()))
                .thenAnswer(i -> ((List<Long>) i.getArguments()[0]).stream()
                        .map(d -> new Experience(d, d.toString(), d.toString(), new Date(), new Date(), null))
                        .collect(Collectors.toList()));
    }

    @Test
    public void addTest() {
        User user = createUser();
        CVRequestModel cvRequest = createCVRequest();
        CV cv = createCv(cvRequest);

        Assertions.assertEquals(cv.getDescription(), cvRequest.description);
        Assertions.assertEquals(cv.getExpectedSalary(), cvRequest.expectedSalary);
        Assertions.assertFalse(cv.isBlocked());
        Assertions.assertEquals(cv.getEducations().get(1).getId(), cvRequest.educations.get(1));
        Assertions.assertEquals(cv.getExperiences().get(1).getId(), cvRequest.experiences.get(1));
        Assertions.assertEquals(cv.getProfessionTags().get(1).getId(), cvRequest.professionTags.get(1));
        Assertions.assertNotNull(cv.getUser());
        Assertions.assertEquals(cv.getUser().getId(), user.getId());
        Assertions.assertEquals(cv.getUser().getUsername(), user.getUsername());
    }

    @Test
    public void updateTest() {
        User user = createUser();
        CVRequestModel oldCvRequest = createCVRequest();
        CV cv = createCv(oldCvRequest);
        CVRequestModel cvRequest = createCVRequest();

        Mockito.when(cvRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> cv);

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId());

        CV updated = cvService.update(cvRequest);

        Assertions.assertEquals(updated.getDescription(), cvRequest.description);
        Assertions.assertEquals(updated.getExpectedSalary(), cvRequest.expectedSalary);
        Assertions.assertFalse(cv.isBlocked());
        Assertions.assertFalse(cv.isDisabled());
        Assertions.assertEquals(updated.getEducations().get(1).getId(), cvRequest.educations.get(1));
        Assertions.assertEquals(updated.getExperiences().get(1).getId(), cvRequest.experiences.get(1));
        Assertions.assertEquals(updated.getProfessionTags().get(1).getId(), cvRequest.professionTags.get(1));
        Assertions.assertNotNull(updated.getUser());
        Assertions.assertEquals(updated.getUser().getId(), user.getId());
        Assertions.assertEquals(updated.getUser().getUsername(), user.getUsername());

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId() + 1);

        Exception differentUser = Assertions.assertThrows(RuntimeException.class, () -> {
            cvService.update(cvRequest);
        });

        Mockito.when(cvRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> null);

        Exception notFound = Assertions.assertThrows(RuntimeException.class, () -> {
            cvService.update(cvRequest);
        });
    }

    @Test
    public void blockUnblockCVTest() {
        CVRequestModel cvRequest = createCVRequest();
        CV cv = createCv(cvRequest);

        Mockito.when(cvRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> cv);

        Assertions.assertFalse(cv.isBlocked());

        cvService.blockCV(cv.getId());
        Assertions.assertTrue(cv.isBlocked());

        cvService.unblockCV(cv.getId());
        Assertions.assertFalse(cv.isBlocked());

        cvService.blockCV(cv.getId());
        Assertions.assertTrue(cv.isBlocked());
    }

    @Test
    public void disableEnableCVTest() {
        CVRequestModel cvRequest = createCVRequest();
        CV cv = createCv(cvRequest);
        User user = createUser();

        Mockito.when(cvRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> cv);

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId());

        Assertions.assertFalse(cv.isDisabled());

        cvService.disableCV(cv.getId());
        Assertions.assertTrue(cv.isDisabled());

        cvService.enableCV(cv.getId());
        Assertions.assertFalse(cv.isDisabled());

        cvService.disableCV(cv.getId());
        Assertions.assertTrue(cv.isDisabled());
    }

    private User createUser() {
        return User.builder()
                .id(1)
                .username("admin")
                .build();
    }

    private CV createCv(CVRequestModel cvRequest) {
        Mockito.when(userRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> createUser());

        return cvService.add(cvRequest);
    }

    private CVRequestModel createCVRequest() {
        CVRequestModel cvRequest = new CVRequestModel();
        cvRequest.description = UUID.randomUUID().toString();
        cvRequest.expectedSalary = new Random().nextDouble();
        cvRequest.professionTags = Arrays.asList(new Random().nextLong(), new Random().nextLong());
        cvRequest.experiences = Arrays.asList(new Random().nextLong(), new Random().nextLong());
        cvRequest.educations = Arrays.asList(new Random().nextLong(), new Random().nextLong());

        return cvRequest;
    }
}
