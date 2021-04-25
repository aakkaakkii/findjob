package ge.find.findjob.api;

import ge.find.findjob.domain.User;
import ge.find.findjob.model.RegistrationRequestModel;

public interface UserRegistrationService {
    User register(RegistrationRequestModel user, String locale);
    User activate(String token);
}
