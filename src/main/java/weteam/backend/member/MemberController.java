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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.auth.AuthService;
import weteam.backend.config.dto.VerifyResponse;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberDto;
import weteam.backend.member.mapper.MemberMapper;

@RestController
@Validated
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "member API")
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/verify/uid/{uid}")
    @Operation(summary = "아이디 중복 확인", responses = {
            @ApiResponse(responseCode = "200",
                         description = "중복 확인 성공",
                         content = @Content(schema = @Schema(implementation = VerifyResponse.class)))
    })
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
