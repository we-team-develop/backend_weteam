package weteam.backend.hash_tag;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.hash_tag.mapper.HashtagMapper;
import weteam.backend.security.util.JwtUtil;
import weteam.backend.security.util.SecurityUtil;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/hashtags")
@RequiredArgsConstructor
@Tag(name = "Hashtag", description = "hashtag API / jwt 필수")
public class HashtagController {
    private final HashtagService hashTagService;
    private final JwtUtil jwtUtil;

    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "해시태그 생성 / 반환값 없음")
    public void create(@RequestBody @Valid HashtagDto request) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        hashTagService.create(request, memberId);
    }

    @GetMapping("/{type}")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "해시태그 조회",
               responses = {@ApiResponse(responseCode = "200", useReturnTypeSchema = true)
               })
    public ResponseEntity<List<HashtagDto.Res>> findByMemberIdWithType(@PathVariable("type") int type) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        List<MemberHashtag> memberHashtagList = hashTagService.findByMemberIdWithType(memberId, type);
        return ResponseEntity.ok(HashtagMapper.instance.toResList(memberHashtagList));
    }


    @PatchMapping("/{memberHashtagId}")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "해시태그 활성화/비활성화, 반환값 업음")
    public void updateUse(@PathVariable("memberHashtagId") Long memberHashtagId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        hashTagService.updateUse(memberHashtagId, memberId);
    }

    @DeleteMapping("/{memberHashtagId}")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "해시태그 삭제/반환값 없음")
    public void delete(@PathVariable("memberHashtagId") Long memberHashtagId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        hashTagService.delete(memberHashtagId, memberId);
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "해시태그 전체 삭제 / 반환값 없음")
    public void deleteAl() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        hashTagService.deleteAllByMemberId(memberId);
    }
}
