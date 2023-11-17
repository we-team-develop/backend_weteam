package weteam.backend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.auth.dto.TokenInfo;
import weteam.backend.auth.util.JwtUtil;
import weteam.backend.member.domain.Member;
import weteam.backend.member.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return memberRepository.findByUid(uid)
                               .map(this::createUserDetails)
                               .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
                   .username(member.getUsername())
                   .password(member.getPassword())
                   .roles(member.getRoles().toArray(String[]::new))
                   .build();
    }

    public TokenInfo createToken(String uid, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(uid, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtUtil.generateToken(authentication);
    }
}
