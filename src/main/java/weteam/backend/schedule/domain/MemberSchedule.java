package weteam.backend.schedule.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import weteam.backend.member.domain.Member;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String memo;

    @Column(nullable = false)
    private Date startedAt;

    @Column(nullable = false)
    private Date endedAt;

    @Column(nullable = false)
    private Date alarm;

//    @Column(name = "repeat_type")
    private Integer repeatType;

    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Category category;
}
