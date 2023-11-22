package weteam.backend.hash_tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.hash_tag.domain.Hashtag;
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.hash_tag.mapper.HashtagMapper;
import weteam.backend.hash_tag.repository.HashtagRepository;
import weteam.backend.hash_tag.repository.MemberHashtagCustomRepository;
import weteam.backend.hash_tag.repository.MemberHashtagRepository;
import weteam.backend.member.MemberService;
import weteam.backend.member.domain.Member;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HashtagService {
    private final HashtagRepository hashTagRepository;
    private final MemberHashtagRepository memberHashtagRepository;
    private final MemberHashtagCustomRepository memberHashtagCustomRepository;
    private final MemberService memberService;

    public void createHashtag(HashtagDto request, Long memberId) {
        Optional<Hashtag> data = hashTagRepository.findByName(request.getName());
        Member member = memberService.loadMemberById(memberId);

        if (data.isEmpty()) {
            Hashtag hashtag = HashtagMapper.instance.toEntity(request);
            MemberHashtag memberHashtag = MemberHashtag.builder()
                                                       .hashtag(hashtag)
                                                       .member(member)
                                                       .build();
            hashTagRepository.save(hashtag);
            memberHashtagRepository.save(memberHashtag);
        } else {
            Hashtag hashtag = data.get();
            if (memberHashtagRepository.findByHashtag(hashtag).isEmpty()) {
                MemberHashtag memberHashtag = MemberHashtag.builder()
                                                           .hashtag(hashtag)
                                                           .member(member)
                                                           .build();
                memberHashtagRepository.save(memberHashtag);
            } else {
                throw new RuntimeException("이미 작성된 해시태그입니다.");
            }
        }
    }

    public List<MemberHashtag> findByMemberIdWithType(Long memberId, int type) {
        return memberHashtagCustomRepository.findByMemberIdWithType(memberId, type);
    }

    public void updateHashtagUse(Long memberHashtagId, Long memberId) {
        MemberHashtag memberHashtag = checkHashtag(memberHashtagId, memberId);
        memberHashtag.setUse(!memberHashtag.isUse());
        memberHashtagRepository.save(memberHashtag);
    }

    public void deleteHashtag(Long memberHashtagId, Long memberId) {
        MemberHashtag memberHashtag = checkHashtag(memberHashtagId, memberId);
        memberHashtagRepository.delete(memberHashtag);
    }

    public void deleteAllByMemberId(Long memberId) {
        memberHashtagRepository.deleteAllByMemberId(memberId);
    }

    public MemberHashtag checkHashtag(Long memberHashtagId, Long memberId) {
        MemberHashtag memberHashtag = memberHashtagRepository.findById(memberHashtagId)
                                                             .orElseThrow(() -> new RuntimeException("없는 해시태그입니다."));
        if (!memberHashtag.getMember().getId().equals(memberId)) {
            throw new RuntimeException("다른 사람의 해시태크입니다.");
        }
        return memberHashtag;
    }
}
