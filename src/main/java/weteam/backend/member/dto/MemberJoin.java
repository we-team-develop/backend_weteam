package weteam.backend.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MemberJoin {
    @NotBlank(message = "uid 누락")
    @Size(min = 5,max = 11)
    @Schema(description = "사용자 아이디", nullable = false, example = "test1234")
    private String uid;

    @NotBlank(message = "username 누락")
    @Size(min = 1,max = 11)
    @Schema(description = "사용자 이름", nullable = false, example = "홍유진")
    private String username;

    @NotBlank(message = "password 누락")
    @Size(min = 4,max = 50)
    @Schema(description = "사용자 비밀번호", nullable = false, example = "11111111")
    private String password;

    @NotBlank(message = "nickname 누락")
    @Size(min = 1,max = 50)
    @Schema(description = "사용자 닉네임", nullable = false, example = "nickname1")
    private String nickname;

    @Schema(description = "아이디 중복 확인여부", nullable = false, example = "true")
    private boolean verifyUsername;

    @Schema(description = "닉네임 중복 확인여부", nullable = false, example = "true")
    private boolean verifyNickname;
}
