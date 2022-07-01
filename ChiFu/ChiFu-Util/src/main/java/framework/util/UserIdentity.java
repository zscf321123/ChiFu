package framework.util;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import framework.model.CfUserM;

@Component
public class UserIdentity {

    private final SpringUser EMPTY_USER = new SpringUser(new CfUserM());

    private SpringUser getSpringUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return principal.equals("anonymousUser")
                ? EMPTY_USER
                : (SpringUser) principal;
    }

    public boolean isAnonymous() {
        return EMPTY_USER.equals(getSpringUser());
    }

    public int getId() {
        return getSpringUser().getId();
    }

    public String getName() {
        return getSpringUser().getName();
    }

    public String getEmail() {
        return getSpringUser().getUsername();
    }
}