package weteam.backend.schedule.repository;

import weteam.backend.schedule.domain.MemberSchedule;

import java.util.List;

public interface MemberScheduleCustomRepository {
    List<MemberSchedule> findByMonth(int month, Long memberId);
}
