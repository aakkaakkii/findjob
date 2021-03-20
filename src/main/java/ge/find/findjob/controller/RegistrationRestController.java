package ge.find.findjob.controller;

import ge.find.findjob.api.UserRegistrationService;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.RegistrationRequestModel;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/registration")
@RequiredArgsConstructor
public class RegistrationRestController {
    private final UserRegistrationService userRegistrationService;

    @PostMapping
    public User register(@RequestBody @Valid RegistrationRequestModel user) {
        return userRegistrationService.register(user);
    }

    @GetMapping("/activate/{token}")
    public void activate(@PathVariable String token) {
        userRegistrationService.activate(token);
    }
}
