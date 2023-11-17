package weteam.backend.hash_tag;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/hashtags")
@RequiredArgsConstructor
@Tag(name = "Hashtag", description = "hashtag API")
public class HashtagController {
    private final HashtagService hashTagService;
}
