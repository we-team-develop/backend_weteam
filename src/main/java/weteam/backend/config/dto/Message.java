package weteam.backend.config.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Message<T>{
    @Schema(description = "요청 결과", nullable = false, example = "true")
    private boolean result;

    @Schema(description = "상태 코드", nullable = false, example = "200")
    private HttpStatus httpStatus;

    @Schema(description = "메세지", nullable = false, example = "요청 성공")
    private String message;

    @Schema(description = "데이터")
    private T data ;
}
