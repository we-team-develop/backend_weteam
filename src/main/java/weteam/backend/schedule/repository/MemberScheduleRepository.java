package weteam.backend.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.schedule.domain.MemberSchedule;

public interface MemberScheduleRepository extends JpaRepository<MemberSchedule,Long> {
}