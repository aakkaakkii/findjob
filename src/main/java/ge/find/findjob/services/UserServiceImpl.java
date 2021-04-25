package ge.find.findjob.services;

import ge.find.findjob.api.UserService;
import ge.find.findjob.domain.Role;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.UserProfileRequest;
import ge.find.findjob.repo.UserRepository;
import ge.find.findjob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional(readOnly = true)
    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> loadUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("already exists");
        }

        if(user.getEmail() != null && userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("already exists");
        }

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        //TODO
        return null;
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> loadRoles() {
        return Arrays.asList(Role.values());
    }

    @Override
    public User getCurrentUser() {
        return securityUtil.getCurrentUser();
    }

    @Override
    public User updateProfile(UserProfileRequest userProfileRequest) {
        User user = getCurrentUser();
        user.setNickname(userProfileRequest.nickname);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActiveUsers() {
        userRepository
                .findAllByActiveIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
                .forEach(user -> {
                    log.debug("Deleting not activated user {}", user.getUsername());
                    userRepository.delete(user);
                });
    }
}
