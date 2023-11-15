package weteam.backend.member;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberJoin;
import weteam.backend.member.mapper.MemberMapper;

@SpringBootTest
class MemberServiceTest {

    @Test
    void join() {
        MemberJoin memberJoin = MemberJoin.builder()
                                          .username("user1")
                                          .password("1111")
                                          .nickname("nickname1")
                                          .build();
        System.out.println("req : " + memberJoin.toString());
        Member member = MemberMapper.instance.toEntity(memberJoin);
        System.out.println(member.toString());
    }
}