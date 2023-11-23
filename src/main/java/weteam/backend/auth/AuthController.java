package weteam.backend.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weteam.backend.auth.dto.AuthDto;
import weteam.backend.security.util.JwtUtil;

@RestController
@Validated
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "auth API")
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CustomAuthService customAuthService;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/join")
    @Operation(summary = "회원가입")
    public void join(@RequestBody @Valid AuthDto.Join request) {
        authService.join(request);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public String login(@RequestBody @Valid AuthDto.Login request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUid(), request.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtUtil.generateToken(authentication, 2L);
    }
    //
    //    @PostMapping("/login")
    //    @Operation(summary = "로그인",
    //               description = "uid, password를 사용한 일반 로그인",
    //               responses = {
    //                       @ApiResponse(responseCode = "200",
    //                                    content = @Content(schema = @Schema(implementation = MemberDto.LoginRes
    //                                    .class)))
    //               })
    //    @Transactional
    //    public ResponseEntity<MemberDto.LoginRes> login(@RequestBody @Valid MemberDto.Login request) {
    //        Member member = memberService.login(request);
    //        String jwt = authService.createToken(request.getUid(), request.getPassword(), member.getId());
    //        return ResponseEntity.ok(MemberMapper.instance.toLoginRes(member, jwt));
    //    }
}
