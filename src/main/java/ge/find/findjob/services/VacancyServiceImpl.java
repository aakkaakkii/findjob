package ge.find.findjob.services;

import ge.find.findjob.api.VacancyService;
import ge.find.findjob.domain.Vacancy;
import ge.find.findjob.domain.VacancyType;
import ge.find.findjob.model.VacancyRequestModel;
import ge.find.findjob.repo.OrganisationRepository;
import ge.find.findjob.repo.ProfessionRepository;
import ge.find.findjob.repo.VacancyRepository;
import ge.find.findjob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final ProfessionRepository professionRepository;
    private final OrganisationRepository organisationRepository;
    private final SecurityUtil securityUtil;
    private final CacheManager cacheManager;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = VacancyService.VACANCY_LOAD_CACHE, key = "#page", unless = "#page > 1")
    public Page<Vacancy> load(int page, int limit) {
        return vacancyRepository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "creationTime")));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = VacancyService.VACANCY_LOAD_CACHE, key = "#page", unless = "#page > 1")
    public Page<Vacancy> loadNonBlocked(int page, int limit) {
        return vacancyRepository.findAllByBlockedIsFalse(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "creationTime")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vacancy> loadByOrganisationId(long id) {
        return vacancyRepository.findByOrganisationId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Vacancy get(long id) {
        return vacancyRepository.getOne(id);
    }

    @Override
    public List<VacancyType> loadVacancyTypes() {
        return Arrays.asList(VacancyType.values());
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

        clearVacancyCache();

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

        clearVacancyCache();

        return vacancyRepository.save(vacancy);
    }

    @Override
    public void delete(long id) {
        Vacancy vacancy = vacancyRepository.getOne(id);

        if (vacancy.getOrganisation() != null && vacancy.getOrganisation().getUser().getId() != securityUtil.getCurrentUserId()) {
            throw new RuntimeException("different user");
        }
        clearVacancyCache();
        vacancyRepository.delete(vacancy);
    }

    @Override
    public void adminDelete(long id) {
        vacancyRepository.deleteById(id);
        clearVacancyCache();
    }

    @Override
    public Vacancy blockVacancy(long id) {
        Vacancy vacancy = vacancyRepository.getOne(id);
        vacancy.setBlocked(true);
        clearVacancyCache();
        return vacancyRepository.save(vacancy);
    }

    @Override
    public Vacancy unblockVacancy(long id) {
        Vacancy vacancy = vacancyRepository.getOne(id);
        vacancy.setBlocked(false);
        clearVacancyCache();
        return vacancyRepository.save(vacancy);
    }

    private void clearVacancyCache() {
        Cache cache = cacheManager.getCache(VACANCY_LOAD_CACHE);
        if(cache != null) {
            cache.clear();
        }
    }
}
