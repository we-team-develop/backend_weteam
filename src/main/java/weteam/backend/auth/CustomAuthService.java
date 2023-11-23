package weteam.backend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.auth.domain.Auth;
import weteam.backend.auth.repository.AuthRepository;
import weteam.backend.security.util.JwtUtil;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomAuthService implements UserDetailsService {
    private final AuthRepository authRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return authRepository.findByUid(uid)
                             .map(this::createUserDetails)
                             .orElseThrow(() -> new RuntimeException("없는 사용자"));
    }

    private UserDetails createUserDetails(Auth auth) {
        return User.builder()
                   .username(auth.getId().toString())
                   .password(auth.getPassword())
                   .roles(auth.getRoles().toArray(String[]::new))
                   .build();
    }
}
