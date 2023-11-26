package weteam.backend.security.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import weteam.backend.auth.domain.Auth;

import java.util.stream.Collectors;

@Getter
public class CustomUser extends User {
    private Auth auth;
    public CustomUser(Auth auth) {
        super(String.valueOf(auth.getId()), auth.getPassword(), auth.getRoles()
                                                                    .stream()
                                                                    .map(SimpleGrantedAuthority::new)
                                                                    .collect(Collectors.toList()));
        this.auth = auth;
    }
}
