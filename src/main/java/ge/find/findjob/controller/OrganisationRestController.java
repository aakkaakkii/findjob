package ge.find.findjob.controller;

import ge.find.findjob.api.OrganisationService;
import ge.find.findjob.domain.Organisation;
import ge.find.findjob.model.OrganisationRequestModel;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/organisations")
@RequiredArgsConstructor
public class OrganisationRestController {
    private final OrganisationService organisationService;

    @GetMapping
    public List<Organisation> load() {
        return organisationService.load();
    }

    @GetMapping("{id}")
    public Organisation get(@PathVariable long id) {
        return organisationService.get(id);
    }

    @PostMapping
    public Organisation add(@RequestBody OrganisationRequestModel requestModel) {
        return organisationService.add(requestModel);
    }

    @PutMapping("/{id}")
    public Organisation update(@PathVariable long id, @RequestBody OrganisationRequestModel requestModel) {
        return organisationService.update(requestModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        organisationService.delete(id);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void adminDelete(@PathVariable long id) {
        organisationService.adminDelete(id);
    }

    @PutMapping("/admin/{id}/block")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Organisation block(@PathVariable long id) {
        return organisationService.blockOrganisation(id);
    }


    @PutMapping("/admin/{id}/unblock")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Organisation unblock(@PathVariable long id) {
        return organisationService.unblockOrganisation(id);
    }
}
