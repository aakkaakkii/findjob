package ge.find.findjob.services;

import ge.find.findjob.api.EducationService;
import ge.find.findjob.domain.Education;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.EducationRequestModel;
import ge.find.findjob.repo.EducationRepository;
import ge.find.findjob.repo.UserRepository;
import ge.find.findjob.util.SecurityUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class EducationServiceTest {
    @Mock
    private EducationRepository educationRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityUtil securityUtil;
    private EducationService educationService;

    @Before
    public void init() {
        educationService = new EducationServiceImpl(educationRepository, userRepository, securityUtil);

        Mockito.when(educationRepository.save(Mockito.any(Education.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void addTest() {
        User user = createUser();
        EducationRequestModel educationRequest = createEducationRequest();
        Education education = createEducation(educationRequest);

        Assertions.assertEquals(education.getSchool(), educationRequest.school);
        Assertions.assertEquals(education.getDegree(), educationRequest.degree);
        Assertions.assertEquals(education.getStartDate(), educationRequest.startDate);
        Assertions.assertEquals(education.getEndDate(), educationRequest.endDate);
        Assertions.assertNotNull(education.getUser());
        Assertions.assertEquals(education.getUser().getId(), user.getId());
        Assertions.assertEquals(education.getUser().getUsername(), user.getUsername());
    }

    @Test
    public void updateTest() {
        User user = createUser();
        EducationRequestModel oldEducationRequest = createEducationRequest();
        Education education = createEducation(oldEducationRequest);
        EducationRequestModel educationRequest = createEducationRequest();

        Mockito.when(educationRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> education);

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> 1L);

        Education updated = educationService.update(educationRequest);

        Assertions.assertEquals(updated.getSchool(), educationRequest.school);
        Assertions.assertEquals(updated.getDegree(), educationRequest.degree);
        Assertions.assertEquals(updated.getStartDate(), educationRequest.startDate);
        Assertions.assertEquals(updated.getEndDate(), educationRequest.endDate);
        Assertions.assertEquals(updated.getUser().getId(), user.getId());
        Assertions.assertEquals(updated.getUser().getUsername(), user.getUsername());

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> 3L);

        Exception differentUser = Assertions.assertThrows(RuntimeException.class, ()-> {
            educationService.update(educationRequest);
        });

        Mockito.when(educationRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> null);

        Exception notFound = Assertions.assertThrows(RuntimeException.class, ()-> {
            educationService.update(educationRequest);
        });
    }

    private User createUser() {
        return User.builder()
                .id(1)
                .username("admin")
                .build();
    }

    private Education createEducation(EducationRequestModel educationRequest) {
        Mockito.when(userRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> createUser());

        return educationService.add(educationRequest);
    }

    private EducationRequestModel createEducationRequest() {
        EducationRequestModel educationRequest = new EducationRequestModel();
        educationRequest.school = UUID.randomUUID().toString();
        educationRequest.degree = UUID.randomUUID().toString();
        educationRequest.startDate = new Date(Math.abs(System.currentTimeMillis() - new Random().nextLong()));
        educationRequest.endDate = new Date(Math.abs(System.currentTimeMillis() - new Random().nextLong()));

        return educationRequest;
    }
}
