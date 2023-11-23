package weteam.backend.member.domain;

import jakarta.persistence.*;
import lombok.*;
import weteam.backend.auth.domain.Auth;
import weteam.backend.config.domain.BaseEntity;
import weteam.backend.hash_tag.domain.MemberHashtag;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Member extends BaseEntity{
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    private String organization;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Auth auth;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private  List<MemberHashtag> memberHashtagList = new ArrayList<>();
}
