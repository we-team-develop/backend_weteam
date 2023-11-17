package weteam.backend.hash_tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.hash_tag.repository.HashtagRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class HashtagService {
    private final HashtagRepository hashTagRepository;

//    public saveHashtag()
}
