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
import weteam.backend.auth.util.JwtUtil;
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.hash_tag.mapper.HashtagMapper;
import weteam.backend.schedule.dto.MemberScheduleDto;

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
               description = "name, type을 사용한 해시태그 생성",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation =
                                            HashtagDto.Res.class))))
               })
    @PreAuthorize("hasAnyRole('USER')")
    public void createHashtag(@RequestBody @Valid HashtagDto request, Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        hashTagService.createHashtag(request, memberId);
    }

    @GetMapping("/{type}")
    @Operation(summary = "해시태그 조회",
               description = "type을 사용한 해시태그 조회",
               responses = {@ApiResponse(responseCode = "200",
                                         content = @Content(array = @ArraySchema(schema = @Schema(implementation =
                                                 HashtagDto.Res.class))))
               })
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<HashtagDto.Res>> findByMemberIdWithType(@PathVariable("type") int type,
                                                                       Principal principal) {

        Long memberId = Long.valueOf(principal.getName());
        List<MemberHashtag> memberHashtagList = hashTagService.findByMemberIdWithType(memberId, type);
        return ResponseEntity.ok(HashtagMapper.instance.toResList(memberHashtagList));
    }


    @PatchMapping("/{memberHashtagId}/{type}")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "해시태그 활성화/비활성화",
               description = "클릭을 통한 해시태그의 활성화/비활성화 수정 후 타입에 맞는 해시태그 리스트 반환",
               responses = {@ApiResponse(responseCode = "200",
                                         content = @Content(array = @ArraySchema(schema = @Schema(implementation =
                                                 HashtagDto.Res.class))))
               })
    public ResponseEntity<List<HashtagDto.Res>> updateHashtagUser(@PathVariable("memberHashtagId") Long memberHashtagId,
                                                                  @PathVariable("type") int type,
                                                                  Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        hashTagService.updateHashtagUse(memberHashtagId, memberId);
        List<MemberHashtag> memberHashtagList = hashTagService.findByMemberIdWithType(memberId, type);
        return ResponseEntity.ok(HashtagMapper.instance.toResList(memberHashtagList));
    }

    @DeleteMapping("/{memberHashtagId}/{type}")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "해시태그 삭제/반환값 없음")
    public ResponseEntity<List<HashtagDto.Res>> deleteHashtag(@PathVariable("memberHashtagId") Long memberHashtagId,
                                                              @PathVariable("type") int type,
                                                              Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        hashTagService.deleteHashtag(memberHashtagId, memberId);
        List<MemberHashtag> memberHashtagList = hashTagService.findByMemberIdWithType(memberId, type);
        return ResponseEntity.ok(HashtagMapper.instance.toResList(memberHashtagList));
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "해시태그 전체 삭제", description = "전체 삭제, 반환값 없음")
    public void deleteAll(Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        hashTagService.deleteAllByMemberId(memberId);
    }
}
