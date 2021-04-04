package ge.find.findjob.services;

import ge.find.findjob.api.ExperienceService;
import ge.find.findjob.domain.Experience;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.ExperienceRequestModel;
import ge.find.findjob.repo.ExperienceRepository;
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
public class ExperienceServiceTest {
    @Mock
    private ExperienceRepository experienceRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SecurityUtil securityUtil;
    private ExperienceService experienceService;

    @Before
    public void init() {
        experienceService = new ExperienceServiceImpl(experienceRepository, userRepository, securityUtil);

        Mockito.when(experienceRepository.save(Mockito.any(Experience.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void addTest() {
        User user = createUser();
        ExperienceRequestModel experienceRequest = createExperienceRequest();
        Experience experience = createExperience(experienceRequest);

        Assertions.assertEquals(experience.getTitle(), experienceRequest.title);
        Assertions.assertEquals(experience.getDescription(), experienceRequest.description);
        Assertions.assertEquals(experience.getStartDate(), experienceRequest.startDate);
        Assertions.assertEquals(experience.getEndDate(), experienceRequest.endDate);
        Assertions.assertNotNull(experience.getUser());
        Assertions.assertEquals(experience.getUser().getId(), user.getId());
        Assertions.assertEquals(experience.getUser().getUsername(), user.getUsername());
    }

    @Test
    public void updateTest() {
        User user = createUser();
        ExperienceRequestModel oldExperienceRequest = createExperienceRequest();
        Experience experience = createExperience(oldExperienceRequest);
        ExperienceRequestModel experienceRequest = createExperienceRequest();


        Mockito.when(experienceRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> experience);

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId());

        Experience updated = experienceService.update(experienceRequest);

        Assertions.assertEquals(updated.getTitle(), experienceRequest.title);
        Assertions.assertEquals(updated.getDescription(), experienceRequest.description);
        Assertions.assertEquals(updated.getStartDate(), experienceRequest.startDate);
        Assertions.assertEquals(updated.getEndDate(), experienceRequest.endDate);
        Assertions.assertEquals(updated.getUser().getId(), user.getId());
        Assertions.assertEquals(updated.getUser().getUsername(), user.getUsername());

        Mockito.when(securityUtil.getCurrentUserId())
                .thenAnswer(i -> user.getId() + 1);

        Exception differentUser = Assertions.assertThrows(RuntimeException.class, () -> {
            experienceService.update(experienceRequest);
        });

        Mockito.when(experienceRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> null);

        Exception notFound = Assertions.assertThrows(RuntimeException.class, () -> {
            experienceService.update(experienceRequest);
        });
    }

    private User createUser() {
        return User.builder()
                .id(1)
                .username("admin")
                .build();
    }

    private Experience createExperience(ExperienceRequestModel experienceRequest) {
        Mockito.when(userRepository.getOne(Mockito.any(Long.class)))
                .thenAnswer(i -> createUser());

        return experienceService.add(experienceRequest);
    }

    private ExperienceRequestModel createExperienceRequest() {
        ExperienceRequestModel experienceRequest = new ExperienceRequestModel();
        experienceRequest.title = UUID.randomUUID().toString();
        experienceRequest.description = UUID.randomUUID().toString();
        experienceRequest.startDate = new Date(Math.abs(System.currentTimeMillis() - new Random().nextLong()));
        experienceRequest.endDate = new Date(Math.abs(System.currentTimeMillis() - new Random().nextLong()));

        return experienceRequest;
    }
}
