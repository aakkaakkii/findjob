package ge.find.findjob.controller;

import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/auth")
@RequiredArgsConstructor
public class AuthController {
}
