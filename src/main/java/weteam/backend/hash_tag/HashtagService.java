package weteam.backend.hash_tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.hash_tag.repository.HashtagRepository;
import weteam.backend.hash_tag.repository.MemberHashtagRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class HashtagService {
    private final HashtagRepository hashTagRepository;
    private final MemberHashtagRepository memberHashtagRepository;

    public void saveHashtag(HashtagDto.create request, Long memberId){

    }
}
