package weteam.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.user.repository.UserRepository;
import weteam.backend.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                             .map(this::createSecurityUser)
                             .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
    }

    public org.springframework.security.core.userdetails.User createSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                                                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                                                        .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                        user.getPassword(),
                        grantedAuthorities);
    }
}
