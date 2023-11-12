package weteam.backend.group_project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.group_project.domain.GroupProject;
import weteam.backend.group_project.repository.GroupProjectRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupProjectService {
    private final GroupProjectRepository groupProjectRepository;

    public void createProject(GroupProject groupProject) {
        groupProjectRepository.save(groupProject);
    }

//    public String createInviteUrl(Long projectId) {
//
//    }
}
