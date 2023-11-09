package weteam.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.user.domain.dto.JoinRequest;
import weteam.backend.user.domain.dto.LoginRequest;
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

    public void join(JoinRequest request) {
        if (!request.isVerifyUsername() || !request.isVerifyNickname()) {
            throw new RuntimeException("중복확인 필수");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = UserMapper.instance.toEntity(request);
        user.getRoles().add("USER");
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public String verifyUsername(String username) {
        return this.findByUsername(username).isPresent() ? "중복된 아이디입니다." : "사용 가능한 아이디입니다";
    }

    public String verifyNickname(String nickname) {
        return this.findByNickname(nickname).isPresent() ? "중복된 닉네임입니다." : "사용 가능한 닉네임입니다";
    }
}

