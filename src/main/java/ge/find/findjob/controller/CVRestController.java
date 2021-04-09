package ge.find.findjob.controller;

import ge.find.findjob.api.CVService;
import ge.find.findjob.domain.CV;
import ge.find.findjob.model.CVRequestModel;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/cvs")
@RequiredArgsConstructor
public class CVRestController {
    private final CVService cvService;

    @GetMapping
    public List<CV> load() {
        return cvService.load();
    }

    @GetMapping("{id}")
    public CV get(@PathVariable long id) {
        return cvService.get(id);
    }

    @GetMapping("/user/{id}")
    public List<CV> loadByUserId(@PathVariable long id) {
        return cvService.loadByUserId(id);
    }

    @PostMapping
    public CV add(@RequestBody CVRequestModel requestModel) {
        return cvService.add(requestModel);
    }

    @PutMapping("{id}")
    public CV update(@PathVariable long id, @RequestBody CVRequestModel requestModel) {
        return cvService.update(requestModel);
    }

    @PutMapping("/{id}/disable")
    public CV disable(@PathVariable long id) {
        return cvService.disableCV(id);
    }

    @PutMapping("/{id}/enable")
    public CV enable(@PathVariable long id) {
        return cvService.enableCV(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        cvService.delete(id);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void adminDelete(@PathVariable long id) {
        cvService.adminDelete(id);
    }

    @PutMapping("/admin/{id}/block")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CV block(@PathVariable long id) {
        return cvService.blockCV(id);
    }

    @PutMapping("/admin/{id}/unblock")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CV unblock(@PathVariable long id) {
        return cvService.unblockCV(id);
    }

}
