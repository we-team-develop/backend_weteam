//package weteam.backend.schedule;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.Message;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import weteam.backend.schedule.domain.MemberSchedule;
//import weteam.backend.schedule.dto.MemberScheduleDto;
//import weteam.backend.schedule.mapper.MemberScheduleMapper;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@Validated
//@RequestMapping("/api/schedules/members")
//@RequiredArgsConstructor
//@Tag(name = "Member Schedule", description = "member schedules API / jwt 필수")
//public class MemberScheduleController {
//    private final MemberScheduleService memberScheduleService;
//
//    @PostMapping("")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "개인스케줄 생성 / 반환값 없음")
//    public void create(@RequestBody @Valid MemberScheduleDto request, Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        memberScheduleService.create(request, memberId);
//    }
//
//    @GetMapping("/{year}/{month}")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "월별 스케줄 조회", responses = {
//            @Message(responseCode = "200",
//                         content = @Content(array = @ArraySchema(schema = @Schema(implementation =
//                                 MemberScheduleDto.Res.class))))
//    })
//    public ResponseEntity<List<MemberScheduleDto.Res>> findByMonth(@PathVariable("year") int year,
//                                                                   @PathVariable("month") int month,
//                                                                   Principal principal) {
//
//        Long memberId = Long.valueOf(principal.getName());
//        List<MemberSchedule> memberScheduleList = memberScheduleService.findByMonth(year, month, memberId);
//        List<MemberScheduleDto.Res> resList = MemberScheduleMapper.instance.toResList(memberScheduleList);
//        return ResponseEntity.ok(resList);
//    }
//
//    @GetMapping("/{year}/{month}/{day}")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "일별 스케줄 조회", responses = {
//            @Message(responseCode = "200",
//                         content = @Content(array = @ArraySchema(schema = @Schema(implementation =
//                                 MemberScheduleDto.Res.class))))
//    })
//    public ResponseEntity<List<MemberScheduleDto.Res>> findByMonth(@PathVariable("year") int year,
//                                                                   @PathVariable("month") int month,
//                                                                   @PathVariable("day") int day,
//                                                                   Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        List<MemberSchedule> memberScheduleList = memberScheduleService.findByDay(year, month, day, memberId);
//        List<MemberScheduleDto.Res> resList = MemberScheduleMapper.instance.toResList(memberScheduleList);
//        return ResponseEntity.ok(resList);
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "id에 맞는 스케줄 조회", responses = {
//            @Message(responseCode = "200", content = @Content(schema = @Schema(implementation =
//                    MemberScheduleDto.Res.class)))
//    })
//    public ResponseEntity<MemberScheduleDto.Res> loadById(@PathVariable("id") Long id, Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        MemberSchedule memberScheduleList = memberScheduleService.loadById(id, memberId);
//        MemberScheduleDto.Res res = MemberScheduleMapper.instance.toRes(memberScheduleList);
//        return ResponseEntity.ok(res);
//    }
//
//    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "스케줄 수정 / 반환값 없음")
//    public void update(@PathVariable("id") Long id,
//                       @RequestBody @Valid MemberScheduleDto request,
//                       Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        memberScheduleService.update(request, id, memberId);
//    }
//
//    @PatchMapping("/{id}/done")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "스케줄 완료 처리 / 반환값 없음")
//    public void update(@PathVariable("id") Long id, Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        memberScheduleService.updateIsDone(id, memberId);
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER')")
//    @Operation(summary = "스케줄 삭제 / 반환값 없음")
//    public void delete(@PathVariable("id") Long id, Principal principal) {
//        Long memberId = Long.valueOf(principal.getName());
//        memberScheduleService.delete(id, memberId);
//    }
//}
