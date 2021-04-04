package ge.find.findjob.services;

import ge.find.findjob.api.EducationService;
import ge.find.findjob.domain.Education;
import ge.find.findjob.model.EducationRequestModel;
import ge.find.findjob.repo.EducationRepository;
import ge.find.findjob.repo.UserRepository;
import ge.find.findjob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    public List<Education> loadUserEducation(long userId) {
        return educationRepository.findByUserId(securityUtil.getCurrentUserId());
    }

    @Override
    public Education get(long id) {
        return educationRepository.getOne(id);
    }

    @Override
    public Education add(EducationRequestModel educationRequest) {
        Education education = Education.builder()
                .degree(educationRequest.degree)
                .school(educationRequest.school)
                .startDate(educationRequest.startDate)
                .endDate(educationRequest.endDate)
                .user(userRepository.getOne(securityUtil.getCurrentUserId()))
                .build();

        return educationRepository.save(education);
    }

    @Override
    public Education update(EducationRequestModel educationRequest) {
        Education education = educationRepository.getOne(educationRequest.id);

        if(education.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        education.setSchool(educationRequest.school);
        education.setDegree(educationRequest.degree);
        education.setStartDate(educationRequest.startDate);
        education.setEndDate(educationRequest.endDate);

        return educationRepository.save(education);
    }

    @Override
    public void delete(long id) {
        Education education = educationRepository.getOne(id);

        if (education.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        educationRepository.delete(education);
    }

    @Override
    public void adminDelete(long id) {
        educationRepository.deleteById(id);
    }
}
