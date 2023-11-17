package weteam.backend.hash_tag;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.auth.util.JwtUtil;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberDto;

import java.security.Principal;

@RestController
@Validated
@RequestMapping("/api/hashtags")
@RequiredArgsConstructor
@Tag(name = "Hashtag", description = "hashtag API")
public class HashtagController {
    private final HashtagService hashTagService;
    private final JwtUtil jwtUtil;

    @PostMapping("/save")
    @Operation(summary = "로그인",
               description = "uid, password를 사용한 일반 로그인",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = MemberDto.Res.class)))
               })
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String> saveHashtag(@RequestBody @Valid HashtagDto.create request, Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        hashTagService.saveHashtag(request,memberId);
        return ResponseEntity.ok("저장 완료");
    }
}
