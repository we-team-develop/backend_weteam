package weteam.backend.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.member.MemberService;
import weteam.backend.member.domain.Member;
import weteam.backend.schedule.domain.MemberSchedule;
import weteam.backend.schedule.dto.MemberScheduleDto;
import weteam.backend.schedule.mapper.MemberScheduleMapper;
import weteam.backend.schedule.repository.MemberScheduleRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberScheduleService {
    private final MemberScheduleRepository memberScheduleRepository;
    private final MemberService memberService;

    public void createSchedule(MemberScheduleDto request, Long memberId) {
        Member member = memberService.loadMemberById(memberId);
        MemberSchedule memberSchedule = MemberScheduleMapper.instance.toEntity(request, member);
        memberScheduleRepository.save(memberSchedule);
    }
}
