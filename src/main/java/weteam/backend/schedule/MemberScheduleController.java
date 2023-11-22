package weteam.backend.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weteam.backend.schedule.dto.MemberScheduleDto;

import java.security.Principal;

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
}
