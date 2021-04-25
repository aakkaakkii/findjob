package ge.find.findjob.controller;

import ge.find.findjob.api.UserRegistrationService;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.RegistrationRequestModel;
import ge.find.findjob.util.ApiConstants;
import ge.find.findjob.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/registration")
@RequiredArgsConstructor
public class RegistrationRestController {
    private final UserRegistrationService userRegistrationService;

    @Resource
    private HttpServletRequest request;

    @PostMapping
    public User register(@RequestBody @Valid RegistrationRequestModel user) {
        return userRegistrationService.register(user, AppUtil.getLanguageCode(request));
    }

    @GetMapping("/activate/{token}")
    public void activate(@PathVariable String token) {
        userRegistrationService.activate(token);
    }
}
