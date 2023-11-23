package weteam.backend.config.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
public class ApiResponse<T>{
    @Schema(description = "요청 결과", nullable = false, example = "true")
    private boolean result;

    @Schema(description = "메세지", nullable = false, example = "회원가입 성공")
    private String message;

    @Schema(description = "데이터")
    private T date;
}
