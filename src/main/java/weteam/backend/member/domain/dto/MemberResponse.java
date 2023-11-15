package weteam.backend.member.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MemberResponse {
    @NotNull(message = "uid 누락")
    @Schema(description = "사용자 아이디", nullable = false, example = "user1111")
    private String uid;

    @NotBlank(message = "username 누락")
    @Schema(description = "사용자 이름", nullable = false, example = "홍유진")
    private String username;

    @NotBlank(message = "nickname 누락")
    @Schema(description = "사용자 닉네임", nullable = false, example = "nickname111")
    private String nickname;
}
