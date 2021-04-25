package ge.find.findjob.services;

import ge.find.findjob.domain.User;
import ge.find.findjob.model.RegistrationRequestModel;
import ge.find.findjob.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTests {
    @Mock
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
    private UserRegistrationServiceImpl userService;
    @Mock
    private MailService mailService;

    @Before
    public void init() {
        userService = new UserRegistrationServiceImpl(userRepository, passwordEncoder, mailService);
    }

    @Test
    public void registrationTest() {
        String username = "admin";
        String nickname = "admin nickname";
        String password = "password";
        String email = "admin@gmail.com";

        User user = saveUser(username, nickname, password, password, email);

        RuntimeException passwordDontMatch = Assertions.assertThrows(RuntimeException.class, ()->{
            saveUser(username, nickname, password, "anotherPassword", email);
        });
        Assertions.assertEquals(user.getUsername(), username);
        Assertions.assertTrue(passwordEncoder.matches(password, user.getPassword()));
        Assertions.assertEquals(user.getEmail(), email);
        Assertions.assertEquals(user.getNickname(), nickname);
        Assertions.assertTrue(user.isAccountNonLocked());
        Assertions.assertFalse(user.isActive());
        Assertions.assertFalse(user.isAdmin());
    }

    @Test
    public void userAlreadyExists() {
        String username = "admin";
        String nickname = "admin nickname";
        String password = "password";
        String email = "admin@gmail.com";

        User user = saveUser(username, nickname, password, password, email);

        Mockito.when(userRepository.findByUsername(Mockito.any(String.class)))
                .thenAnswer(i -> username.equals(i.getArguments()[0]) ? user : null);

        Mockito.when(userRepository.findByEmail(Mockito.any(String.class)))
                .thenAnswer(i -> email.equals(i.getArguments()[0]) ? user : null);

        RuntimeException usernameIsSame = Assertions.assertThrows(RuntimeException.class, ()->{
            saveUser(username, nickname, password, password, "email2");
        });

        RuntimeException emailIsSame = Assertions.assertThrows(RuntimeException.class, ()->{
            saveUser("username2", nickname, password, password, email);
        });
    }

    @Test
    public void activateUser() {
        String username = "admin";
        String nickname = "admin nickname";
        String password = "password";
        String email = "admin@gmail.com";

        User user = saveUser(username, nickname, password, password, email);


        Mockito.when(userRepository.findByUsername(Mockito.any(String.class)))
                .thenAnswer(i -> username.equals(i.getArguments()[0]) ? user : null);

        User activatedUser = userService.activate(user.createToken());
        Assertions.assertTrue(activatedUser.isActive() );

    }

    private User saveUser(String username, String nickname, String password, String repeatPassword, String email) {
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        RegistrationRequestModel user = RegistrationRequestModel.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .repeatPassword(repeatPassword)
                .email(email)
                .build();

        return userService.register(user, null);
    }
}
