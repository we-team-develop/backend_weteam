package weteam.backend.user.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class LoginRequest {
    @NotBlank(message = "username 누락")
    private String username;

    @NotBlank(message = "password 누락")
    private String password;
}
