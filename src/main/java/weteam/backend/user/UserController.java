package weteam.backend.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.auth.AuthService;
import weteam.backend.auth.domain.TokenInfo;
import weteam.backend.common.domain.dto.VerifyResponse;
import weteam.backend.user.domain.User;
import weteam.backend.user.domain.dto.UserJoin;
import weteam.backend.user.domain.dto.UserLogin;
import weteam.backend.user.domain.dto.UserResponse;
import weteam.backend.user.mapper.UserMapper;

@RestController
@Validated
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "user API")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/join")
    @Operation(summary = "회원가입",
               description = "아이디, 닉네임 중복확인 후 입력된 데이터를 사용하여 회원가입을 한다",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = UserResponse.class)))
               })
    public ResponseEntity<UserResponse> join(@RequestBody @Valid UserJoin request) {
        return ResponseEntity.ok(UserMapper.instance.toRes(userService.join(request)));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인",
               description = "username, password를 사용한 일반 로그인",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = UserResponse.class)))
               })
    public ResponseEntity<TokenInfo> login(@RequestBody @Valid UserLogin request) {
        User user = userService.login(request);
        return ResponseEntity.ok(authService.createToken(request.getUsername(), request.getPassword()));
    }

    @Operation(summary = "아이디 중복 확인", responses = {
            @ApiResponse(responseCode = "200",
                         description = "중복 확인 성공",
                         content = @Content(schema = @Schema(implementation = VerifyResponse.class)))
    })
    @GetMapping("/verify/username/{username}")
    public ResponseEntity<VerifyResponse> verifyUsername(@Size(min = 5, max = 50) @PathVariable("username")
                                                         String username) {

        return ResponseEntity.ok(userService.verifyUsername(username) ?
                                 VerifyResponse.builder().result(false).message("중복된 아이디입니다.").build() :
                                 VerifyResponse.builder().result(true).message("사용 가능한 아이디입니다.").build());
    }

    @GetMapping("/verify/nickname/{nickname}")
    @Operation(summary = "닉네임 중복 확인", responses = {
            @ApiResponse(responseCode = "200",
                         description = "중복 확인 성공",
                         content = @Content(schema = @Schema(implementation = VerifyResponse.class)))
    })
    public ResponseEntity<VerifyResponse> verifyNickname(@Size(min = 5, max = 50) @PathVariable("nickname") String nickname) {
        return ResponseEntity.ok(userService.verifyNickname(nickname) ?
                                 VerifyResponse.builder().result(false).message("중복된 닉네임입니다.").build() :
                                 VerifyResponse.builder().result(true).message("사용 가능한 닉네임입니다.").build());
    }
}
