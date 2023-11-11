package weteam.backend.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import weteam.backend.user.domain.User;
import weteam.backend.user.domain.dto.UserJoin;
import weteam.backend.user.mapper.UserMapper;

@SpringBootTest
class UserServiceTest {

    @Test
    void join() {
        UserJoin userJoin = UserJoin.builder()
                                    .username("user1")
                                    .password("1111")
                                    .nickname("nickname1")
                                    .build();
        System.out.println("req : " + userJoin.toString());
        User user = UserMapper.instance.toEntity(userJoin);
        System.out.println(user.toString());
    }
}