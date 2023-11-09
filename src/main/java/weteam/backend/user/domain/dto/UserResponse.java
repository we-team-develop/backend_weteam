package weteam.backend.user.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    @NotNull(message = "id 누락")
    private Long id;

    @NotBlank(message = "username 누락")
    private String username;

    @NotBlank(message = "nickname 누락")
    private String nickname;
}
