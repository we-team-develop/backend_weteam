package weteam.backend.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByNickname(String nickname);
}
