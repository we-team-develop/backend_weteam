package weteam.backend.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.auth.AuthService;
import weteam.backend.auth.dto.TokenInfo;
import weteam.backend.config.dto.VerifyResponse;
import weteam.backend.member.domain.Member;
import weteam.backend.member.dto.MemberDto;
import weteam.backend.member.mapper.MemberMapper;
import weteam.backend.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public MemberDto.Res join(MemberDto.Join request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Member member = MemberMapper.instance.toEntity(request, hashedPassword, List.of("USER"));
        return MemberMapper.instance.toRes(memberRepository.save(member));
    }

    public MemberDto.Res login(MemberDto.Login request) {
        TokenInfo tokenInfo = authService.createToken(request.getUid(), request.getPassword());
        Member member = findByUid(request.getUid())
                .orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));
        return MemberMapper.instance.toRes(member,tokenInfo);
    }
    public Optional<Member> findByUid(String username) {
        return memberRepository.findByUid(username);
    }

    public Optional<Member> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }
    public VerifyResponse verifyUid(String uid) {
        return this.findByUid(uid).isPresent() ?
               VerifyResponse.builder().result(false).message("중복된 아이디입니다.").build() :
               VerifyResponse.builder().result(true).message("사용 가능한 아이디입니다.").build();
    }
    public VerifyResponse verifyNickname(String nickname) {
        return this.findByNickname(nickname).isPresent() ?
               VerifyResponse.builder().result(false).message("중복된 닉네임입니다.").build() :
               VerifyResponse.builder().result(true).message("사용 가능한 닉네임입니다.").build();
    }
}

