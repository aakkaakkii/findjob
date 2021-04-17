package ge.find.findjob.controller;

import ge.find.findjob.api.EducationService;
import ge.find.findjob.domain.Education;
import ge.find.findjob.model.EducationRequestModel;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/educations")
@RequiredArgsConstructor
public class EducationRestController {
    private final EducationService educationService;

    @GetMapping("/{id}")
    public Education get(@PathVariable long id) {
        return educationService.get(id);
    }

    @GetMapping("/user/{id}")
    public List<Education> loadUserEducations(@PathVariable long id) {
        return educationService.loadUserEducation(id);
    }

    @GetMapping("/currentUser")
    public List<Education> loadUserEducations() {
        return educationService.loadCurrentUserEducation();
    }

    @PostMapping
    public Education add(@RequestBody EducationRequestModel requestModel) {
        return educationService.add(requestModel);
    }

    @PutMapping("/{id}")
    public Education update(@PathVariable long id, @RequestBody EducationRequestModel requestModel) {
        return educationService.update(requestModel);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void adminDelete(@PathVariable long id) {
        educationService.adminDelete(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        educationService.delete(id);
    }


}
