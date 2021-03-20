package ge.find.findjob.api;

import ge.find.findjob.model.ChangePasswordRequestModel;
import ge.find.findjob.model.RegistrationRequestModel;

public interface AuthService {
    void registerUser(RegistrationRequestModel registration);
    void changePassword(ChangePasswordRequestModel changeModel);
    void resetPassword(String email);
    void activate(String activationCode);
    //TODO
    void updateProfile();
}
