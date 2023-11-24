package weteam.backend.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.config.dto.Message;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberDto;
import weteam.backend.member.mapper.MemberMapper;
import weteam.backend.security.domain.CustomUser;
import weteam.backend.security.util.JwtUtil;
import weteam.backend.security.util.SecurityUtil;

import java.security.Principal;

@RestController
@Validated
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "member API")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "다른 사용자 조회",responses = {
            @ApiResponse(responseCode = "200",
                         content = @Content(schema = @Schema(implementation = MemberDto.Res.class))),
    })
    public ResponseEntity<Message> findById(@PathVariable("id") Long id) {
        Member member = memberService.findMyInfoWithUseHashtag(id);
        MemberDto.Res res = MemberMapper.instance.toRes(member);

        return ResponseEntity.ok(Message.of("내 정보 조회 성공", res));
    }
    
    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "내 정보 조회",responses = {
            @ApiResponse(responseCode = "200",
                         content = @Content(schema = @Schema(implementation = MemberDto.Res.class))),
    })
    public ResponseEntity<Message> findMyInfo() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberService.findMyInfoWithUseHashtag(memberId);
        MemberDto.Res res = MemberMapper.instance.toRes(member);

        return ResponseEntity.ok(Message.of("내 정보 조회 성공", res));
    }
}
