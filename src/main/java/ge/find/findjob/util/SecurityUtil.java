package ge.find.findjob.util;

import ge.find.findjob.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public User getCurrentUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
            return (User) user;
        }
        return null;
    }

    public long getCurrentUserId() {
        User user = getCurrentUser();
        if (user != null) {
            return user.getId();
        }
        return -1;
    }
}
