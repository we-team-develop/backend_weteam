package weteam.backend.schedule.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import weteam.backend.member.domain.Member;

import java.time.LocalDateTime;
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
    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String place;

    private LocalDateTime alarm;

    private Integer repeatType;

    private String memo;

    private String color;

    @ColumnDefault("false")
    private boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Category category;
}
