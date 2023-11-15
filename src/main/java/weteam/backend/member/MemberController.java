package weteam.backend.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.auth.AuthService;
import weteam.backend.auth.domain.TokenInfo;
import weteam.backend.common.domain.dto.VerifyResponse;
import weteam.backend.member.domain.Member;
import weteam.backend.member.domain.dto.MemberJoin;
import weteam.backend.member.domain.dto.MemberLogin;
import weteam.backend.member.domain.dto.MemberResponse;
import weteam.backend.member.mapper.MemberMapper;

@RestController
@Validated
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "member API")
public class MemberController {
    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/join")
    @Operation(summary = "회원가입",
               description = "아이디, 닉네임 중복확인 후 입력된 데이터를 사용하여 회원가입을 한다",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = MemberResponse.class)))
               })
    public ResponseEntity<MemberResponse> join(@RequestBody @Valid MemberJoin request) {
        MemberResponse memberResponse = MemberMapper.instance.toRes(memberService.join(request));
        System.out.println(2);
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인",
               description = "uid, password를 사용한 일반 로그인",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = MemberResponse.class)))
               })
    public ResponseEntity<TokenInfo> login(@RequestBody @Valid MemberLogin request) {
        Member member = memberService.login(request);
        return ResponseEntity.ok(authService.createToken(request.getUid(), request.getPassword()));
    }

    @Operation(summary = "아이디 중복 확인", responses = {
            @ApiResponse(responseCode = "200",
                         description = "중복 확인 성공",
                         content = @Content(schema = @Schema(implementation = VerifyResponse.class)))
    })
    @GetMapping("/verify/uid/{uid}")
    public ResponseEntity<VerifyResponse> verifyUsername(@Size(min = 1, max = 50) @PathVariable("uid")
                                                         String uid) {

        return ResponseEntity.ok(memberService.verifyUid(uid) ?
                                 VerifyResponse.builder().result(false).message("중복된 아이디입니다.").build() :
                                 VerifyResponse.builder().result(true).message("사용 가능한 아이디입니다.").build());
    }

    @GetMapping("/verify/nickname/{nickname}")
    @Operation(summary = "닉네임 중복 확인", responses = {
            @ApiResponse(responseCode = "200",
                         description = "중복 확인 성공",
                         content = @Content(schema = @Schema(implementation = VerifyResponse.class)))
    })
    public ResponseEntity<VerifyResponse> verifyNickname(@Size(min = 1, max = 50) @PathVariable("nickname") String nickname) {
        return ResponseEntity.ok(memberService.verifyNickname(nickname) ?
                                 VerifyResponse.builder().result(false).message("중복된 닉네임입니다.").build() :
                                 VerifyResponse.builder().result(true).message("사용 가능한 닉네임입니다.").build());
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('USER')")
    public String test() {
        return "test success!!";
    }
}
