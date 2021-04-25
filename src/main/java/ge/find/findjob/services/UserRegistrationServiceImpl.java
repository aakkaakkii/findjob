package ge.find.findjob.services;

import ge.find.findjob.api.UserRegistrationService;
import ge.find.findjob.domain.Role;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.RegistrationRequestModel;
import ge.find.findjob.repo.UserRepository;
import ge.find.findjob.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Override
    public User register(RegistrationRequestModel user, String locale) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("already exists");
        }

        if (user.getEmail() != null && userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("already exists");
        }

        if (!user.password.equals(user.repeatPassword)) {
            throw new RuntimeException("password dont match");
        }

        User newUser = new User();

        newUser.setUsername(user.username);
        newUser.setNickname(user.nickname);
        newUser.setEmail(user.email);
        newUser.setActive(false);
        newUser.setPassword(passwordEncoder.encode(user.password));
        newUser.setRoles(Collections.singleton(Role.USER));

        //TODO
        String activationToken = newUser.createToken();
        System.out.println("activation token: " + activationToken);
        newUser =  userRepository.save(newUser);

        mailService.sendActivationEmail(newUser, activationToken, locale);

        return newUser;
    }

    @Override
    public User activate(String token) {
        if (JwtTokenProvider.validateToken(token)) {
            User user = userRepository.findByUsername(JwtTokenProvider.getUsername(token));

            if (user != null) {
                user.setActive(true);
                return userRepository.save(user);
            }
        }
        return null;
    }
}
