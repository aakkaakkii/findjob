package ge.find.findjob.api;

import ge.find.findjob.domain.Role;
import ge.find.findjob.domain.User;
import ge.find.findjob.model.UserProfileRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getById(long id);
    List<User> loadUsers();
    User createUser(User user);
    User updateUser(User user);
    void deleteUserById(long id);
    List<Role> loadRoles();
    User getCurrentUser();
    User updateProfile(UserProfileRequest userProfileRequest);
}
