package ge.find.findjob.api;

import ge.find.findjob.domain.Vacancy;
import ge.find.findjob.domain.VacancyType;
import ge.find.findjob.model.VacancyRequestModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {
    Page<Vacancy> load(int page, int limit);
    List<Vacancy> loadByOrganisationId(long id);
    Vacancy get(long id);
    List<VacancyType> loadVacancyTypes();
    Vacancy add(VacancyRequestModel vacancyRequestModel);
    Vacancy update(VacancyRequestModel vacancyRequestModel);
    void delete(long id);
    void adminDelete(long id);
    Vacancy blockVacancy(long id);
    Vacancy unblockVacancy(long id);
}
