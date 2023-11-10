package weteam.backend.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "사용자 번호", nullable = false, example = "1L")
    private Long id;

    @NotBlank(message = "username 누락")
    @Schema(description = "사용자 아이디", nullable = false, example = "user11111")
    private String username;

    @NotBlank(message = "nickname 누락")
    @Schema(description = "사용자 닉네임", nullable = false, example = "nickname111")
    private String nickname;
}
