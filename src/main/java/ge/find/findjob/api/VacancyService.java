package ge.find.findjob.api;

import ge.find.findjob.domain.Vacancy;
import ge.find.findjob.model.VacancyRequestModel;

import java.util.List;

public interface VacancyService {
    List<Vacancy> load();
    List<Vacancy> loadByOrganisationId(long id);
    Vacancy get(long id);
    Vacancy add(VacancyRequestModel vacancyRequestModel);
    Vacancy update(VacancyRequestModel vacancyRequestModel);
    void delete(long id);
    void adminDelete(long id);
    Vacancy blockVacancy(long id);
    Vacancy unblockVacancy(long id);
}
