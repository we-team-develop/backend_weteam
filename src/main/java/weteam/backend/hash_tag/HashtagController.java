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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.auth.util.JwtUtil;
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.hash_tag.mapper.HashtagMapper;

import java.security.Principal;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/hashtags")
@RequiredArgsConstructor
@Tag(name = "Hashtag", description = "hashtag API / jwt 필수")
public class HashtagController {
    private final HashtagService hashTagService;
    private final JwtUtil jwtUtil;

    @PostMapping("/save")
    @Operation(summary = "해시태그 생성",
               description = "name, type을 사용한 해시태그 생성 / jwt 필수",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = HashtagDto.ResList.class)))
               })
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String> saveHashtag(@RequestBody @Valid HashtagDto.Create request, Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        hashTagService.saveHashtag(request,memberId);
        return ResponseEntity.ok("저장 완료");
    }

    @GetMapping("/{type}")
    @Operation(summary = "해시태그 조회",
               description = "type을 사용한 해시태그 조회 / jwt 필수",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = HashtagDto.ResList.class)))
               })
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity <List<HashtagDto.Res>> findByMemberIdWithType(@PathVariable("type") int type,
                                                                        Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        List<MemberHashtag> memberHashtagList = hashTagService.findByMemberIdWithType(memberId, type);
        return ResponseEntity.ok(HashtagMapper.instance.toResList(memberHashtagList));
    }

    @PatchMapping("/{memberHashtagId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String> updateHashtagUser(@PathVariable("memberHashtagId") Long memberHashtagId,
                                                    Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        hashTagService.updateHashtagUse(memberHashtagId, memberId);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{memberHashtagId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String> deleteHashtag(@PathVariable("memberHashtagId") Long memberHashtagId,
                                                Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        hashTagService.deleteHashtag(memberHashtagId, memberId);
        return ResponseEntity.ok("success");
    }
}
