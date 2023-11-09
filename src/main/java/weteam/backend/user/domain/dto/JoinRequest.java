package weteam.backend.user.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class JoinRequest {
    @NotBlank(message = "username 누락")
    private String username;

    @NotBlank(message = "password 누락")
    private String password;

    @NotBlank(message = "nickname 누락")
    private String nickname;

    private boolean verifyUsername;

    private boolean verifyNickname;
}
