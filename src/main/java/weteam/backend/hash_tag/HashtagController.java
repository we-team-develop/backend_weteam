//package weteam.backend.hash_tag;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import weteam.backend.auth.util.JwtUtil;
//import weteam.backend.hash_tag.domain.MemberHashtag;
//import weteam.backend.hash_tag.dto.HashtagDto;
//import weteam.backend.hash_tag.mapper.HashtagMapper;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@Validated
//@RequestMapping("/api/hashtags")
//@RequiredArgsConstructor
//@Tag(name = "Hashtag", description = "hashtag API / jwt 필수")
//public class HashtagController {
//    private final HashtagService hashTagService;
//    private final JwtUtil jwtUtil;
//
//    @PostMapping("")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "해시태그 생성 / 반환값 없음")
//    public void create(@RequestBody @Valid HashtagDto request, Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        hashTagService.create(request, memberId);
//    }
//
//    @GetMapping("/{type}")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "해시태그 조회",
//               description = "type을 사용한 해시태그 조회",
//               responses = {@ApiResponse(responseCode = "200",
//                                         content = @Content(array = @ArraySchema(schema = @Schema(implementation =
//                                                 HashtagDto.Res.class))))
//               })
//    public ResponseEntity<List<HashtagDto.Res>> findByMemberIdWithType(@PathVariable("type") int type,
//                                                                       Principal principal) {
//
//        Long memberId = Long.valueOf(principal.getName());
//        List<MemberHashtag> memberHashtagList = hashTagService.findByMemberIdWithType(memberId, type);
//        return ResponseEntity.ok(HashtagMapper.instance.toResList(memberHashtagList));
//    }
//
//
//    @PatchMapping("/{memberHashtagId}/{type}")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "해시태그 활성화/비활성화, 반환값 업음")
//    public void updateUse(@PathVariable("memberHashtagId") Long memberHashtagId,
//                                                                  @PathVariable("type") int type,
//                                                                  Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        hashTagService.updateUse(memberHashtagId, memberId);
//    }
//
//    @DeleteMapping("/{memberHashtagId}/{type}")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "해시태그 삭제/반환값 없음")
//    public void delete(@PathVariable("memberHashtagId") Long memberHashtagId,
//                       @PathVariable("type") int type,
//                       Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        hashTagService.delete(memberHashtagId, memberId);
//    }
//
//    @DeleteMapping("/all")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "해시태그 전체 삭제 / 반환값 없음")
//    public void deleteAll(Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        hashTagService.deleteAllByMemberId(memberId);
//    }
//}
