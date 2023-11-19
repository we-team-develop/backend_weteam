package weteam.backend.hash_tag.domain;

import jakarta.persistence.*;
import lombok.*;
import weteam.backend.member.domain.Member;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isUse;

    @ManyToOne(fetch =  FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch =  FetchType.LAZY)
    private Hashtag hashtag;
}
