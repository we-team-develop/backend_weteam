package weteam.backend.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserJoin {
    @NotBlank(message = "username 누락")
    @Size(min = 8,max = 50)
    @Schema(description = "사용자 아이디", nullable = false, example = "user11111")
    private String username;

    @NotBlank(message = "password 누락")
    @Size(min = 8,max = 50)
    @Schema(description = "사용자 비밀번호", nullable = false, example = "11111111")
    private String password;

    @NotBlank(message = "nickname 누락")
    @Size(min = 8,max = 50)
    @Schema(description = "사용자 닉네임", nullable = false, example = "nickname1")
    private String nickname;

    @NotBlank(message = "name 누락")
    @Size(min = 1,max = 50)
    @Schema(description = "사용자 성명", nullable = false, example = "김김수수한한무무거거북이")
    private String name;

    @Schema(description = "아이디 중복 확인여부", nullable = false, example = "true")
    private boolean verifyUsername;

    @Schema(description = "닉네임 중복 확인여부", nullable = false, example = "true")
    private boolean verifyNickname;
}
