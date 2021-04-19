package ge.find.findjob.controller;

import ge.find.findjob.api.VacancyService;
import ge.find.findjob.domain.Vacancy;
import ge.find.findjob.domain.VacancyType;
import ge.find.findjob.model.VacancyRequestModel;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/vacancies")
@RequiredArgsConstructor
public class VacancyRestController {
    private final VacancyService vacancyService;

    @GetMapping
    public List<Vacancy> load() {
        return vacancyService.load();
    }

    @GetMapping("{id}")
    public Vacancy get(@PathVariable long id) {
        return vacancyService.get(id);
    }

    @GetMapping("/organisation/{id}")
    public List<Vacancy> loadByOrganisationId(@PathVariable long id) {
        return vacancyService.loadByOrganisationId(id);
    }

    @GetMapping("/vacancyTypes")
    public List<VacancyType> loadVacancyTypes() {
        return vacancyService.loadVacancyTypes();
    }

    @PostMapping
    public Vacancy add(@RequestBody VacancyRequestModel requestModel) {
        return vacancyService.add(requestModel);
    }

    @PutMapping("{id}")
    public Vacancy update(@PathVariable long id, @RequestBody VacancyRequestModel requestModel) {
        return vacancyService.update(requestModel);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        vacancyService.delete(id);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void adminDelete(@PathVariable long id) {
        vacancyService.adminDelete(id);
    }

    @PutMapping("/admin/{id}/block")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Vacancy block(@PathVariable long id) {
        return vacancyService.blockVacancy(id);
    }

    @PutMapping("/admin/{id}/unblock")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Vacancy unblock(@PathVariable long id) {
        return vacancyService.unblockVacancy(id);
    }
}
