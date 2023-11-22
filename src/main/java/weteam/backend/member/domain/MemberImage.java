package weteam.backend.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberImage {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false)
    private String url;
}
