package weteam.backend.hash_tag.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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

    @ColumnDefault("false")
    private boolean isUse;

    private String color;

    @ManyToOne(fetch =  FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch =  FetchType.LAZY)
    private Hashtag hashtag;
}
