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

    @Override
    public User register(RegistrationRequestModel user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("already exists");
        }

        if (user.getEmail() != null && userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("already exists");
        }

        if (user.password != user.repeatPassword) {
            throw new RuntimeException("password dont match");
        }

        User newUser = new User();

        newUser.setUsername(user.username);
        newUser.setEmail(user.email);
        newUser.setActive(false);
        newUser.setPassword(passwordEncoder.encode(user.password));
        newUser.setRoles(Collections.singleton(Role.USER));

        //TODO
        String activationToken = newUser.createToken();
        System.out.println("activation token: " + activationToken);

        return userRepository.save(newUser);
    }

    @Override
    public void activate(String token) {
        if (JwtTokenProvider.validateToken(token)) {
            User user = userRepository.findByUsername(JwtTokenProvider.getUsername(token));

            if (user != null) {
                user.setActive(true);
                userRepository.save(user);
            }
        }
    }
}
