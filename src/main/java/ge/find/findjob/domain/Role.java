package ge.find.findjob.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }

    public interface RoleNames{
        String ADMIN = "ADMIN";
        String USER = "USER";
    }
}
