package weteam.backend.hash_tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.hash_tag.domain.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {
}
