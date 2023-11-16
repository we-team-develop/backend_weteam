package weteam.backend.group_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.group_project.domain.GroupProject;

public interface GroupProjectRepository extends JpaRepository<GroupProject,Long> {

}
