package weteam.backend.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weteam.backend.user.domain.dto.JoinRequest;
import weteam.backend.user.domain.dto.LoginRequest;
import weteam.backend.user.domain.dto.UserResponse;
import weteam.backend.user.mapper.UserMapper;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User",description = "User API")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody @Valid JoinRequest request) {
        userService.join(request);
        return ResponseEntity.ok("가입 완료");
    }
    
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid LoginRequest request) {
        // TODO: 후에 jwt 로직으로 교체
        return ResponseEntity.ok(UserMapper.instance.toRes(userService.login(request)));
    }

    @GetMapping("/verify/username/{username}")
    public ResponseEntity<String> verifyUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.verifyUsername(username));
    }

    @GetMapping("/verify/nickname/{nickname}")
    public ResponseEntity<String> verifyNickname(@PathVariable("nickname") String nickname) {
        return ResponseEntity.ok(userService.verifyNickname(nickname));
    }
}
