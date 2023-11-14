package weteam.backend.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.member.domain.Member;
import weteam.backend.member.domain.dto.MemberJoin;
import weteam.backend.member.domain.dto.MemberLogin;
import weteam.backend.member.mapper.MemberMapper;
import weteam.backend.member.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(MemberJoin request) {
        if (!request.isVerifyUsername() || !request.isVerifyNickname()) {
            throw new RuntimeException("중복확인 필수");
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Member member = MemberMapper.instance.toEntity(request);
        member.getRoles().add("USER");
        member.setPassword(hashedPassword);
        return memberRepository.save(member);
    }

    public Member login(MemberLogin request) {
        Member member = findByUsername(request.getUid())
                .orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호를 확인해 주세요");
        }
        return member;
    }
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    public boolean verifyUsername(String username) {
        return this.findByUsername(username).isPresent();
    }

    public boolean verifyNickname(String nickname) {
        return this.findByNickname(nickname).isPresent();
    }
}

