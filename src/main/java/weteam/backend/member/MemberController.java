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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.config.dto.VerifyResponse;
import weteam.backend.member.dto.MemberDto;

@RestController
@Validated
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "member API")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    @Operation(summary = "회원가입",
               description = "아이디, 닉네임 중복확인 후 입력된 데이터를 사용하여 회원가입을 한다",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = MemberDto.Res.class)))
               })
    public ResponseEntity<MemberDto.Res> join(@RequestBody @Valid MemberDto.Join request) {
        return ResponseEntity.ok(memberService.join(request));
    }
    @PostMapping("/login")
    @Operation(summary = "로그인",
               description = "uid, password를 사용한 일반 로그인",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = MemberDto.Res.class)))
               })
    public ResponseEntity<MemberDto.Res> login(@RequestBody @Valid MemberDto.Login request) {
        return ResponseEntity.ok(memberService.login(request));
    }
    @Operation(summary = "아이디 중복 확인", responses = {
            @ApiResponse(responseCode = "200",
                         description = "중복 확인 성공",
                         content = @Content(schema = @Schema(implementation = VerifyResponse.class)))
    })
    @GetMapping("/verify/uid/{uid}")
    public ResponseEntity<VerifyResponse> verifyUsername(@Size(min = 1, max = 50) @PathVariable("uid") String uid) {
        return ResponseEntity.ok(memberService.verifyUid(uid));
    }
    @GetMapping("/verify/nickname/{nickname}")
    @Operation(summary = "닉네임 중복 확인", responses = {
            @ApiResponse(responseCode = "200",
                         description = "중복 확인 성공",
                         content = @Content(schema = @Schema(implementation = VerifyResponse.class)))
    })
    public ResponseEntity<VerifyResponse> verifyNickname(@Size(min = 1, max = 50)
                                                         @PathVariable("nickname") String nickname) {
        return ResponseEntity.ok(memberService.verifyNickname(nickname));
    }
}
