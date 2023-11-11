package weteam.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.common.domain.dto.VerifyResponse;
import weteam.backend.user.domain.dto.UserJoin;
import weteam.backend.user.domain.dto.UserLogin;
import weteam.backend.user.mapper.UserMapper;
import weteam.backend.user.repository.UserRepository;
import weteam.backend.user.domain.User;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User join(UserJoin request) {
        if (!request.isVerifyUsername() || !request.isVerifyNickname()) {
            throw new RuntimeException("중복확인 필수");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = UserMapper.instance.toEntity(request);
        user.getRoles().add("USER");
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User login(UserLogin request) {
        User user = findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호를 확인해 주세요");
        }
        return user;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public boolean verifyUsername(String username) {
        return this.findByUsername(username).isPresent();
    }

    public boolean verifyNickname(String nickname) {
        return this.findByNickname(nickname).isPresent();
    }
}

