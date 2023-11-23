package weteam.backend.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import weteam.backend.config.domain.BaseEntity;
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.score.domain.Score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    private String department;



    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private MemberImage image;

    @OneToOne(mappedBy = "memberId", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Score score;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private  List<MemberHashtag> memberHashtagList = new ArrayList<>();
}
