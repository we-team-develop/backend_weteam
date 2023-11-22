package weteam.backend.member.repository;

import weteam.backend.member.domain.Member;

import java.util.List;

public interface MemberCustomRepository {
    public Member findMemberWithUseHashtagList(String uid);
}
