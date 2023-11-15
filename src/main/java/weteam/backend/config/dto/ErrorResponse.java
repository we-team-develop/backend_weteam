package weteam.backend.config.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {
    private String field;
    private String defaultMessage;
}
