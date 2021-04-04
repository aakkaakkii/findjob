package ge.find.findjob.services;

import ge.find.findjob.api.VacancyService;
import ge.find.findjob.domain.Vacancy;
import ge.find.findjob.model.VacancyRequestModel;
import ge.find.findjob.repo.OrganisationRepository;
import ge.find.findjob.repo.ProfessionRepository;
import ge.find.findjob.repo.VacancyRepository;
import ge.find.findjob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final ProfessionRepository professionRepository;
    private final OrganisationRepository organisationRepository;
    private final SecurityUtil securityUtil;

    @Override
    public List<Vacancy> load() {
        return vacancyRepository.findAll();
    }

    @Override
    public List<Vacancy> loadByOrganisationId(long id) {
        return vacancyRepository.findByOrganisationId(id);
    }

    @Override
    public Vacancy get(long id) {
        return vacancyRepository.getOne(id);
    }

    @Override
    public Vacancy add(VacancyRequestModel vacancyRequestModel) {
        Vacancy vacancy = Vacancy.builder()
                .title(vacancyRequestModel.title)
                .description(vacancyRequestModel.description)
                .creationTime(new Date())
                .salary(vacancyRequestModel.salary)
                .vacancyType(vacancyRequestModel.vacancyType)
                .professionTags(professionRepository.findByIds(vacancyRequestModel.professionTags))
                .organisation(organisationRepository.getOne(vacancyRequestModel.organisationId))
                .blocked(false)
                .build();

        return vacancyRepository.save(vacancy);
    }

    @Override
    public Vacancy update(VacancyRequestModel vacancyRequestModel) {
        Vacancy vacancy = vacancyRepository.getOne(vacancyRequestModel.id);

        if (vacancy.getOrganisation() != null && vacancy.getOrganisation().getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        vacancy.setTitle(vacancyRequestModel.title);
        vacancy.setDescription(vacancyRequestModel.description);
        vacancy.setVacancyType(vacancyRequestModel.vacancyType);
        vacancy.setProfessionTags(professionRepository.findByIds(vacancyRequestModel.professionTags));
        vacancy.setSalary(vacancyRequestModel.salary);

        return vacancyRepository.save(vacancy);
    }

    @Override
    public void delete(long id) {
        Vacancy vacancy = vacancyRepository.getOne(id);

        if (vacancy.getOrganisation() != null && vacancy.getOrganisation().getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }

        vacancyRepository.delete(vacancy);
    }

    @Override
    public void adminDelete(long id) {
        vacancyRepository.deleteById(id);
    }

    @Override
    public Vacancy blockVacancy(long id) {
        Vacancy vacancy = vacancyRepository.getOne(id);
        vacancy.setBlocked(true);
        return vacancyRepository.save(vacancy);
    }

    @Override
    public Vacancy unblockVacancy(long id) {
        Vacancy vacancy = vacancyRepository.getOne(id);
        vacancy.setBlocked(false);
        return vacancyRepository.save(vacancy);
    }
}
