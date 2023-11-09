package weteam.backend.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import weteam.backend.user.domain.User;
import weteam.backend.user.domain.dto.JoinRequest;
import weteam.backend.user.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Test
    void join() {
        JoinRequest joinRequest = JoinRequest.builder()
                                             .username("user1")
                                             .password("1111")
                                             .nickname("nickname1")
                                             .build();
        System.out.println("req : " + joinRequest.toString());
        User user = UserMapper.instance.toEntity(joinRequest);
        System.out.println(user.toString());
    }
}