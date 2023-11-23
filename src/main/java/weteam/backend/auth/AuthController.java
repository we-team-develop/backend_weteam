package weteam.backend.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberDto;
import weteam.backend.member.mapper.MemberMapper;

@RestController
@Validated
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "auth API")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    @Operation(summary = "회원가입")
    public void join(@RequestBody @Valid MemberDto request) {
//        Member member = memberService.join(request);
//        String jwt = authService.createToken(request.getUid(), request.getPassword(), member.getId());
    }
//
//    @PostMapping("/login")
//    @Operation(summary = "로그인",
//               description = "uid, password를 사용한 일반 로그인",
//               responses = {
//                       @ApiResponse(responseCode = "200",
//                                    content = @Content(schema = @Schema(implementation = MemberDto.LoginRes.class)))
//               })
//    @Transactional
//    public ResponseEntity<MemberDto.LoginRes> login(@RequestBody @Valid MemberDto.Login request) {
//        Member member = memberService.login(request);
//        String jwt = authService.createToken(request.getUid(), request.getPassword(), member.getId());
//        return ResponseEntity.ok(MemberMapper.instance.toLoginRes(member, jwt));
//    }
}
