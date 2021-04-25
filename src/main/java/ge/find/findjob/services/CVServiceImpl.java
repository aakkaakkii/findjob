package ge.find.findjob.services;

import ge.find.findjob.api.CVService;
import ge.find.findjob.domain.CV;
import ge.find.findjob.model.CVRequestModel;
import ge.find.findjob.repo.*;
import ge.find.findjob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CVServiceImpl implements CVService {
    private final CVRepository cvRepository;
    private final SecurityUtil securityUtil;
    private final ProfessionRepository professionRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CV> load() {
        return cvRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CV> loadByUserId(long id) {
        return cvRepository.findByUserId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CV get(long id) {
        return cvRepository.getOne(id);
    }

    @Override
    public CV add(CVRequestModel cvRequest) {
        CV cv = CV.builder()
                .description(cvRequest.description)
                .expectedSalary(cvRequest.expectedSalary)
                .creationTime(new Date())
                .professionTags(professionRepository.findByIds(cvRequest.professionTags))
                .educations(educationRepository.findByIds(cvRequest.educations))
                .experiences(experienceRepository.findByIds(cvRequest.experiences))
                .user(userRepository.getOne(securityUtil.getCurrentUserId()))
                .blocked(false)
                .disabled(false)
                .build();

        return cvRepository.save(cv);
    }

    @Override
    public CV update(CVRequestModel cvRequest) {
        CV cv = cvRepository.getOne(cvRequest.id);

        if (cv.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        cv.setDescription(cvRequest.description);
        cv.setExpectedSalary(cvRequest.expectedSalary);
        cv.setProfessionTags(professionRepository.findByIds(cvRequest.professionTags));
        cv.setEducations(educationRepository.findByIds(cvRequest.educations));
        cv.setExperiences(experienceRepository.findByIds(cvRequest.experiences));

        return cvRepository.save(cv);
    }

    @Override
    public void delete(long id) {
        CV cv = cvRepository.getOne(id);

        if (cv.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        cvRepository.delete(cv);
    }

    @Override
    public void adminDelete(long id) {
        cvRepository.deleteById(id);
    }

    @Override
    public CV blockCV(long id) {
        CV cv = cvRepository.getOne(id);
        cv.setBlocked(true);
        return cvRepository.save(cv);
    }

    @Override
    public CV unblockCV(long id) {
        CV cv = cvRepository.getOne(id);
        cv.setBlocked(false);
        return cvRepository.save(cv);
    }

    @Override
    public CV disableCV(long id) {
        CV cv = cvRepository.getOne(id);
        if (cv.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }
        cv.setDisabled(true);
        return cvRepository.save(cv);
    }

    @Override
    public CV enableCV(long id) {
        CV cv = cvRepository.getOne(id);
        if (cv.getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }
        cv.setDisabled(false);
        return cvRepository.save(cv);
    }
}
