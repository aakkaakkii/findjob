package ge.find.findjob.controller;

import ge.find.findjob.api.PasswordService;
import ge.find.findjob.model.ChangePasswordRequestModel;
import ge.find.findjob.model.PasswordRequestModel;
import ge.find.findjob.model.ResetPasswordRequestModel;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/password")
@RequiredArgsConstructor
public class PasswordRestController {
    private final PasswordService passwordService;

    @PostMapping("/reset")
    public void resetPasswordRequest(@RequestBody ResetPasswordRequestModel resetPassword) {
        passwordService.resetPasswordRequest(resetPassword);
    }

    @PostMapping("/reset/{token}")
    public void  resetPassword(@PathVariable String token, @RequestBody PasswordRequestModel password) {
        passwordService.resetPassword(token, password);
    }

    @PostMapping("/change")
    public void changePassword(@RequestBody ChangePasswordRequestModel changePassword) {
        passwordService.changePassword(changePassword);
    }
}
