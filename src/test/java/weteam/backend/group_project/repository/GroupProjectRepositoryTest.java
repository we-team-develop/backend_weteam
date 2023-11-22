package weteam.backend.group_project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import weteam.backend.group_project.domain.GroupWork;

import static org.assertj.core.util.DateUtil.now;

@SpringBootTest
class GroupProjectRepositoryTest {
    @Autowired
    private GroupProjectRepository groupProjectRepository;

    @Autowired
    private GroupWorkRepository groupWorkRepository;

    @Test
    void test1() {
        GroupWork groupWork = GroupWork.builder()
                                       .name("sdad")
                                       .startedAt(now())
                                       .build();
        groupWorkRepository.save(groupWork);
        groupWorkRepository.findAll().forEach(System.out::println);
    }
}