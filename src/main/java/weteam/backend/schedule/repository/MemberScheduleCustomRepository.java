package weteam.backend.schedule.repository;

import weteam.backend.member.domain.Member;
import weteam.backend.schedule.domain.MemberSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MemberScheduleCustomRepository {
    public List<MemberSchedule> findByMonth(LocalDateTime startDate, LocalDateTime endDate, Long memberId);

    public List<MemberSchedule> findByDate(LocalDateTime startDate, LocalDateTime endDate, Long memberId);
}
