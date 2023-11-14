package weteam.backend.member.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MemberLogin {
    @NotBlank(message = "uid 누락")
    @Schema(description = "사용자 아이디", nullable = false, example = "user11111")
    private String uid;

    @NotBlank(message = "password 누락")
    @Schema(description = "사용자 비밀번호", nullable = false, example = "1111")
    private String password;
}
