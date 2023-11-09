package weteam.backend.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weteam.backend.user.domain.dto.JoinRequest;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody @Valid JoinRequest request) {
        return ResponseEntity.ok("가입 완료");
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
