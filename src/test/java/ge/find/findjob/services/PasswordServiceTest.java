package ge.find.findjob.services;

import ge.find.findjob.domain.User;
import ge.find.findjob.model.ChangePasswordRequestModel;
import ge.find.findjob.model.PasswordRequestModel;
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
public class PasswordServiceTest {
    @Mock
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
    private PasswordServiceImpl passwordService;

    @Before
    public void init() {
        passwordService = new PasswordServiceImpl(userRepository, passwordEncoder);;
    }

    @Test
    public void resetPasswordTest() {
        User u = User.builder().username("admin").build();

        PasswordRequestModel passwordRequestModel = new PasswordRequestModel();
        passwordRequestModel.password = "password";
        passwordRequestModel.repeatPassword = "password";

        Mockito.when(userRepository.findByUsername(Mockito.any(String.class)))
                .thenAnswer(i -> u.getUsername().equals(i.getArguments()[0]) ? u : null);

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        User changedUser = passwordService.resetPassword(u.createToken(), passwordRequestModel);
        Assertions.assertTrue(passwordEncoder.matches(passwordRequestModel.password, changedUser.getPassword()));

        passwordRequestModel.password = "anotherPassword";
        User changedUser2 = passwordService.resetPassword(u.createToken(), passwordRequestModel);
        Assertions.assertFalse(passwordEncoder.matches(passwordRequestModel.password, changedUser2.getPassword()));
    }

    @Test
    public void changePasswordTest() {
        User u = User.builder().username("admin").password(passwordEncoder.encode("password")).build();

        Mockito.when(userRepository.findByUsername(Mockito.any(String.class)))
                .thenAnswer(i -> u.getUsername().equals(i.getArguments()[0]) ? u : null);

        ChangePasswordRequestModel changePasswordRequestModel = new ChangePasswordRequestModel();
        changePasswordRequestModel.currentPassword = "password";
        changePasswordRequestModel.newPassword = "password2";
        changePasswordRequestModel.repeatPassword = "password2";

        User changedUser =  passwordService.changePassword(changePasswordRequestModel, u.getUsername());
        Assertions.assertTrue(passwordEncoder.matches(changePasswordRequestModel.newPassword, changedUser.getPassword()));


        changePasswordRequestModel.newPassword = "anotherPassword";
        User changedUser2 =  passwordService.changePassword(changePasswordRequestModel, u.getUsername());
        Assertions.assertFalse(passwordEncoder.matches(changePasswordRequestModel.newPassword, changedUser2.getPassword()));

        changePasswordRequestModel.currentPassword = "anotherPassword";
        User changedUser3 =  passwordService.changePassword(changePasswordRequestModel, u.getUsername());
        Assertions.assertFalse(passwordEncoder.matches(changePasswordRequestModel.newPassword, changedUser3.getPassword()));

    }
}
