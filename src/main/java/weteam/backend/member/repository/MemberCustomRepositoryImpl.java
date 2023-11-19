package weteam.backend.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import weteam.backend.member.domain.Member;

import java.util.List;

import static weteam.backend.hash_tag.domain.QHashtag.hashtag;
import static weteam.backend.hash_tag.domain.QMemberHashtag.memberHashtag;
import static weteam.backend.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public  Member findMemberWithHashtagList(String uid) {
        return jpaQueryFactory.selectFrom(member)
                              .leftJoin(member.memberHashtagList, memberHashtag)
                              .leftJoin(memberHashtag.hashtag, hashtag)
                              .where(member.uid.eq(uid))
                              .fetchOne();
    }
}
