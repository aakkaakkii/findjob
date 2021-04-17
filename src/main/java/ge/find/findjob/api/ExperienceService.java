package ge.find.findjob.api;

import ge.find.findjob.domain.Experience;
import ge.find.findjob.model.ExperienceRequestModel;

import java.util.List;

public interface ExperienceService {
    List<Experience> loadUserExperience(long userId);
    List<Experience> loadCurrentUserExperience();
    Experience get(long id);
    Experience add(ExperienceRequestModel experienceRequest);
    Experience update(ExperienceRequestModel experienceRequest);
    void delete(long id);
    void adminDelete(long id);
}
