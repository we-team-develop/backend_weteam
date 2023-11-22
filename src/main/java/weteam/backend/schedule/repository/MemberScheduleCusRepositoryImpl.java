package weteam.backend.schedule.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import weteam.backend.schedule.domain.MemberSchedule;

import java.util.Date;
import java.util.List;

import static weteam.backend.schedule.domain.QMemberSchedule.memberSchedule;

@Repository
@RequiredArgsConstructor
public class MemberScheduleCusRepositoryImpl implements MemberScheduleCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private Date test = new Date();

    @Override
    public List<MemberSchedule> findByMonth(int month, Long memberId) {
        test = Date.from(2023.11.22);
        return jpaQueryFactory.selectFrom(memberSchedule)
                .where(memberSchedule.member.id.eq(memberId).and(memberSchedule.startedAt.eq(month))
                .fetch();
    }
}
