package ge.find.findjob.controller;

import ge.find.findjob.api.ProfessioTagnService;
import ge.find.findjob.domain.ProfessionTag;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/professionTags")
@RequiredArgsConstructor
public class ProfessionTagRestController {
    private final ProfessioTagnService professioTagnService;

    @GetMapping
    public List<ProfessionTag> load() {
        return professioTagnService.load();
    }

    @GetMapping("/{id}")
    public ProfessionTag get(@PathVariable long id) {
        return professioTagnService.get(id);
    }

    @PostMapping
    public ProfessionTag add(@RequestBody ProfessionTag professionTag) {
        return professioTagnService.add(professionTag);
    }

    @DeleteMapping("/{id}")
    public void add(@PathVariable long id) {
         professioTagnService.delete(id);
    }
}
