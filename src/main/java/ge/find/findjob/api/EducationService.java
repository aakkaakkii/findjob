package ge.find.findjob.api;

import ge.find.findjob.domain.Education;
import ge.find.findjob.model.EducationRequestModel;

import java.util.List;

public interface EducationService {
    List<Education> loadUserEducation(long userId);
    Education get(long id);
    Education add(EducationRequestModel educationRequest);
    Education update(EducationRequestModel educationRequest);
    void delete(long id);
    void adminDelete(long id);
}
