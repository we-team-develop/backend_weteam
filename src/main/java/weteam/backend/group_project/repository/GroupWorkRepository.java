package weteam.backend.group_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.group_project.domain.GroupWork;

public interface GroupWorkRepository extends JpaRepository<GroupWork,Long> {
}
