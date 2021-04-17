package ge.find.findjob.services;

import ge.find.findjob.api.ExperienceService;
import ge.find.findjob.domain.Experience;
import ge.find.findjob.model.ExperienceRequestModel;
import ge.find.findjob.repo.ExperienceRepository;
import ge.find.findjob.repo.UserRepository;
import ge.find.findjob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    public List<Experience> loadUserExperience(long userId) {
        return experienceRepository.findByUserId(userId);
    }

    @Override
    public List<Experience> loadCurrentUserExperience() {
        return experienceRepository.findByUserId(securityUtil.getCurrentUserId());
    }

    @Override
    public Experience get(long id) {
        return experienceRepository.getOne(id);
    }

    @Override
    public Experience add(ExperienceRequestModel experienceRequest) {
        Experience experience = Experience.builder()
                .title(experienceRequest.title)
                .description(experienceRequest.description)
                .startDate(experienceRequest.startDate)
                .endDate(experienceRequest.endDate)
                .user(userRepository.getOne(securityUtil.getCurrentUserId()))
                .build();

        return experienceRepository.save(experience);
    }

    @Override
    public Experience update(ExperienceRequestModel experienceRequest) {
        Experience experience = experienceRepository.getOne(experienceRequest.id);

        if(experience.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        experience.setDescription(experienceRequest.description);
        experience.setTitle(experienceRequest.title);
        experience.setStartDate(experienceRequest.startDate);
        experience.setEndDate(experienceRequest.endDate);

        return experienceRepository.save(experience);
    }

    @Override
    public void delete(long id) {
        Experience experience = experienceRepository.getOne(id);

        if (experience.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        experienceRepository.delete(experience);
    }

    @Override
    public void adminDelete(long id) {
        experienceRepository.deleteById(id);
    }
}
