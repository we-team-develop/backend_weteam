package weteam.backend.hash_tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.hash_tag.domain.MemberHashtag;

public interface MemberHashtagRepository extends JpaRepository<MemberHashtag, Long> {
}
