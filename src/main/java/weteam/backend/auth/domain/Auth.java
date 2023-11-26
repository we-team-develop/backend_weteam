package weteam.backend.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import weteam.backend.config.domain.BaseEntity;
import weteam.backend.member.domain.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Auth {
    @Id
    private Long id;

    @Column(updatable = false)
    private String uid;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Member member;
}
