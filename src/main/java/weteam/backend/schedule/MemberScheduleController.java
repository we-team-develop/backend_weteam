package weteam.backend.schedule;

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
import weteam.backend.schedule.domain.MemberSchedule;
import weteam.backend.schedule.dto.MemberScheduleDto;
import weteam.backend.schedule.mapper.MemberScheduleMapper;

import java.security.Principal;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/schedules/members")
@RequiredArgsConstructor
@Tag(name = "Member Schedule", description = "member schedules API / jwt 필수")
public class MemberScheduleController {
    private final MemberScheduleService memberScheduleService;

    @PostMapping("/create")
    @Operation(summary = "개인스케줄 생성, 반환값 없음")
    @PreAuthorize("hasAnyRole('USER')")
    public void createMemberSchedule(@RequestBody @Valid MemberScheduleDto request, Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        memberScheduleService.createSchedule(request, memberId);
    }

    @GetMapping("/month/{year}/{month}")
    @Operation(summary = "월별 스케줄 조회", responses = {
            @ApiResponse(responseCode = "200",
                         content = @Content(array = @ArraySchema(schema = @Schema(implementation =
                                 MemberScheduleDto.Res.class))))
    })
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<MemberScheduleDto.Res>> findByMonth(@PathVariable("year") int year,
                                                                   @PathVariable("month") int month,
                                                                   Principal principal) {

        Long memberId = Long.valueOf(principal.getName());
        List<MemberSchedule> memberScheduleList = memberScheduleService.findByMonth(year, month, memberId);
        List<MemberScheduleDto.Res> resList = MemberScheduleMapper.instance.toResList(memberScheduleList);
        return ResponseEntity.ok(resList);
    }

    @GetMapping("/day/{year}/{month}/{day}")
    @Operation(summary = "일별 스케줄 조회", responses = {
            @ApiResponse(responseCode = "200",
                         content = @Content(array = @ArraySchema(schema = @Schema(implementation =
                                 MemberScheduleDto.Res.class))))
    })
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<MemberScheduleDto.Res>> findByMonth(@PathVariable("year") int year,
                                                                   @PathVariable("month") int month,
                                                                   @PathVariable("day") int day,
                                                                   Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        List<MemberSchedule> memberScheduleList = memberScheduleService.findByDay(year, month, day, memberId);
        List<MemberScheduleDto.Res> resList = MemberScheduleMapper.instance.toResList(memberScheduleList);
        return ResponseEntity.ok(resList);
    }
}
