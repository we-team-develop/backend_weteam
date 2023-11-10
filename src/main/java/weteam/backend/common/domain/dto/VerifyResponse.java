package weteam.backend.common.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class VerifyResponse {
    @Schema(description = "요청 결과", nullable = false, example = "true")
    private boolean result;

    @Schema(description = "메세지", nullable = false, example = "요청 성공했습니다 :)")
    private String message;
}
