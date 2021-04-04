package ge.find.findjob.controller;


import ge.find.findjob.api.ExperienceService;
import ge.find.findjob.domain.Experience;
import ge.find.findjob.model.ExperienceRequestModel;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/experiences")
@RequiredArgsConstructor
public class ExperienceRestController {
    private final ExperienceService experienceService;

    @GetMapping("/{id}")
    public Experience get(@PathVariable long id) {
        return experienceService.get(id);
    }

    @GetMapping("/user/{id}")
    public List<Experience> loadUserExperiences(@PathVariable long id) {
        return experienceService.loadUserExperience(id);
    }

    @PostMapping
    public Experience add(@RequestBody ExperienceRequestModel requestModel) {
        return experienceService.add(requestModel);
    }

    @PutMapping("/{id}")
    public Experience update(@PathVariable long id, @RequestBody ExperienceRequestModel requestModel) {
        return experienceService.update(requestModel);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void adminDelete(@PathVariable long id) {
        experienceService.adminDelete(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        experienceService.delete(id);
    }
}
