package weteam.backend.hash_tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.hash_tag.domain.Hashtag;
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.hash_tag.mapper.HashtagMapper;
import weteam.backend.hash_tag.repository.HashtagRepository;
import weteam.backend.hash_tag.repository.MemberHashtagRepository;
import weteam.backend.member.MemberService;
import weteam.backend.member.domain.Member;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HashtagService {
    private final HashtagRepository hashTagRepository;
    private final MemberHashtagRepository memberHashtagRepository;
    private final MemberService memberService;

    public void saveHashtag(HashtagDto.create request, Long memberId) {
        Optional<Hashtag> data = hashTagRepository.findByName(request.getName());
        Member member = memberService.findById(memberId).orElseThrow(() -> new RuntimeException("사용자가 없습니다"));

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

        }
    }

}
