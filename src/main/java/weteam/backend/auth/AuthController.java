package weteam.backend.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.auth.dto.AuthDto;
import weteam.backend.config.dto.Message;
import weteam.backend.security.CustomAuthService;
import weteam.backend.security.util.JwtUtil;

@RestController
@Validated
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "auth API")
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CustomAuthService customAuthService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/join")
    @Operation(summary = "회원가입", responses = {
            @ApiResponse(responseCode = "200",
                         content = @Content(schema = @Schema(type = "string", example = "회원가입 성공"))),
            @ApiResponse(responseCode = "400",
                         content = @Content(schema = @Schema(type = "string", example = "사용자 uid 중복")))
    })
    public ResponseEntity<?> join(@RequestBody @Valid AuthDto.Join request) {
        authService.join(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", responses = {
            @ApiResponse(responseCode = "200",
                         content = @Content(schema = @Schema(type = "string", example = "eyJhbGciJI..."))),
            @ApiResponse(responseCode = "400",
                         content = @Content(schema = @Schema(type = "string", example = "자격 증명에 실패했습니다")))

    })
    public ResponseEntity<String> login(@RequestBody @Valid AuthDto.Login request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUid(), request.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String jwt =jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/verify/uid/{uid}")
    @Operation(summary = "아이디 중복확인", responses = {
            @ApiResponse(responseCode = "200",
                         content = @Content(schema = @Schema(implementation = Message.class))),
            @ApiResponse(responseCode = "400",
                         content = @Content(schema = @Schema(type = "string", example = "중복된 아이디")))
    })
    public ResponseEntity<Message> verifyUid(@PathVariable("uid") String uid) {
        String message = authService.verifyUid(uid);
        return ResponseEntity.ok(Message.of(message));
    }

    @GetMapping("/verify/nickname/{nickname}")
    @Operation(summary = "닉네임 중복확인", responses = {
            @ApiResponse(responseCode = "200",
                         content = @Content(schema = @Schema(implementation = Message.class))),
            @ApiResponse(responseCode = "400",
                         content = @Content(schema = @Schema(type = "string", example = "중복된 닉네임")))
    })
    public ResponseEntity<Message> verifyNickname(@PathVariable("nickname") String nickname) {
        String message = authService.verifyNickname(nickname);
        return ResponseEntity.ok(Message.of(message));
    }
}
