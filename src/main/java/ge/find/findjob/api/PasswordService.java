package ge.find.findjob.api;

import ge.find.findjob.model.ChangePasswordRequestModel;
import ge.find.findjob.model.PasswordRequestModel;
import ge.find.findjob.model.ResetPasswordRequestModel;

public interface PasswordService {
    void resetPasswordRequest(ResetPasswordRequestModel resetPasswordRequestModel);
    void resetPassword(String token, PasswordRequestModel password);
    void changePassword(ChangePasswordRequestModel resetPassword, String initiatorUsername);
}
