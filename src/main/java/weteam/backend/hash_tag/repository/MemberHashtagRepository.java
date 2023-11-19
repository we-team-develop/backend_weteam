package weteam.backend.hash_tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.hash_tag.domain.Hashtag;
import weteam.backend.hash_tag.domain.MemberHashtag;

import java.util.Optional;

public interface MemberHashtagRepository extends JpaRepository<MemberHashtag, Long> {
    Optional<MemberHashtag> findByHashtag(Hashtag hashtag);

    Optional<MemberHashtag> findByMemberId(Long emberId);
}
