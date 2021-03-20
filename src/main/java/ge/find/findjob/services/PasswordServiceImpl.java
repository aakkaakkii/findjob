package ge.find.findjob.services;

import ge.find.findjob.api.PasswordService;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.ChangePasswordRequestModel;
import ge.find.findjob.model.PasswordRequestModel;
import ge.find.findjob.model.ResetPasswordRequestModel;
import ge.find.findjob.repo.UserRepository;
import ge.find.findjob.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void resetPasswordRequest(ResetPasswordRequestModel resetPassword) {
        User user = userRepository.findByEmail(resetPassword.email);

        if (user != null) {
            System.out.println("password request : " + user.createToken());
        }
    }

    @Override
    public void resetPassword(String token, PasswordRequestModel password) {
        User user = userRepository.findByUsername(JwtTokenProvider.getUsername(token));

        if (user != null && password.password.equals(password.repeatPassword)) {
            user.setPassword(passwordEncoder.encode(password.password));
            userRepository.save(user);
        }
    }

    @Override
    public void changePassword(ChangePasswordRequestModel resetPassword, String initiatorUsername) {
        User user = userRepository.findByUsername(resetPassword.username);

        if (user != null && user.getUsername().equals(initiatorUsername)
                && passwordEncoder.matches(resetPassword.currentPassword, user.getPassword())
                && resetPassword.currentPassword.equals(resetPassword.newPassword)) {
            user.setPassword(passwordEncoder.encode(resetPassword.newPassword));
            userRepository.save(user);
        }
    }
}
