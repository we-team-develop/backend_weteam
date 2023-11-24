package weteam.backend.member;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "member API")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public void findById(@PathVariable("id") Long id) {
//        return
    }
}
