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
    public User resetPassword(String token, PasswordRequestModel password) {
        User user = userRepository.findByUsername(JwtTokenProvider.getUsername(token));

        if (user != null && password.password.equals(password.repeatPassword)) {
            user.setPassword(passwordEncoder.encode(password.password));
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User changePassword(ChangePasswordRequestModel resetPassword, String initiatorUsername) {
        User user = userRepository.findByUsername(initiatorUsername);

        if (user != null && passwordEncoder.matches(resetPassword.currentPassword, user.getPassword())
                && resetPassword.newPassword.equals(resetPassword.repeatPassword)) {
            user.setPassword(passwordEncoder.encode(resetPassword.newPassword));
            userRepository.save(user);
        }
        return user;
    }
}
