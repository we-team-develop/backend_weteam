package weteam.backend.hash_tag.repository;

import weteam.backend.hash_tag.domain.MemberHashtag;

import java.util.List;

public interface MemberHashtagCustomRepository {
    List<MemberHashtag> findByMemberIdWithType(Long memberId, int type);
}
