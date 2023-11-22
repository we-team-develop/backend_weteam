package weteam.backend.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.member.MemberService;
import weteam.backend.member.domain.Member;
import weteam.backend.schedule.domain.MemberSchedule;
import weteam.backend.schedule.dto.MemberScheduleDto;
import weteam.backend.schedule.mapper.MemberScheduleMapper;
import weteam.backend.schedule.repository.MemberScheduleCustomRepository;
import weteam.backend.schedule.repository.MemberScheduleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberScheduleService {
    private final MemberScheduleRepository memberScheduleRepository;
    private final MemberScheduleCustomRepository memberScheduleCustomRepository;
    private final MemberService memberService;

    public void createSchedule(MemberScheduleDto request, Long memberId) {
        Member member = memberService.loadMemberById(memberId);
        MemberSchedule memberSchedule = MemberScheduleMapper.instance.toEntity(request, member);
        memberScheduleRepository.save(memberSchedule);
    }

    public  List<MemberSchedule> findByMonth(int year, int month, Long memberId) {
        Member member = memberService.loadMemberById(memberId);
        LocalDateTime startDate = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime endDate = startDate.plusMonths(1).minusMinutes(1);
        return memberScheduleCustomRepository.findByMonth(startDate, endDate, memberId);
    }

    public List<MemberSchedule> findByDay(int year, int month, int day, Long memberId) {
        Member member = memberService.loadMemberById(memberId);
        LocalDateTime startDate = LocalDate.of(year, month, day).atStartOfDay();
        LocalDateTime endDate = startDate.plusDays(1).minusMinutes(1);
        return memberScheduleCustomRepository.findByDate(startDate, endDate, memberId);
    }
}
