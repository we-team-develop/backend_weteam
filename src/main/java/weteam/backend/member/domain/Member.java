package weteam.backend.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import weteam.backend.auth.domain.Auth;
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
