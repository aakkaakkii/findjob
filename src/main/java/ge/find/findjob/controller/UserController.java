package ge.find.findjob.controller;

import ge.find.findjob.api.UserService;
import ge.find.findjob.domain.Role;
import ge.find.findjob.domain.User;
import ge.find.findjob.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.APP_REST_PATH + "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/current")
//    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping
    public List<User> loadUsers() {
        return userService.loadUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping
    public User add(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/roles")
    public List<Role> loadRoles() {
        return userService.loadRoles();
    }

}
