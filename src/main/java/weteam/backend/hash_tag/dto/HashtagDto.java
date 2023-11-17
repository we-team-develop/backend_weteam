package weteam.backend.hash_tag.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

public class HashtagDto {
    @Getter
    @Builder
    public static class create {
        @NotBlank(message = "name 누락")
        @Schema(description = "해시태그 이름", nullable = false, example = "밤샘인간")
        private String name;
        
        @Min(value = 1, message = "type은 1~4")
        @Max(value = 4, message = "type은 1~4")
        @Schema(description = "해시태그 타입 1: 희망업무, 2: mbti, 3: 특기, 4: 성격, 5: 기타", nullable = false, example = "1")
        private int type;
    }
    @Getter
    @Builder
    public static class Res{
        private String name;
        private String type;
    }
}
