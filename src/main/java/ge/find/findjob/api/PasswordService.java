package ge.find.findjob.api;

import ge.find.findjob.domain.User;
import ge.find.findjob.model.ChangePasswordRequestModel;
import ge.find.findjob.model.PasswordRequestModel;
import ge.find.findjob.model.ResetPasswordRequestModel;

public interface PasswordService {
    void resetPasswordRequest(ResetPasswordRequestModel resetPasswordRequestModel);
    User resetPassword(String token, PasswordRequestModel password);
    User changePassword(ChangePasswordRequestModel resetPassword);
}
