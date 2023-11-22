package weteam.backend.score.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import weteam.backend.group_project.domain.GroupProject;
import weteam.backend.member.domain.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScoreLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member scoredMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member scorerMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupProject groupProjectList;
}
