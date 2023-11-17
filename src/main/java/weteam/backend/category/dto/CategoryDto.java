package weteam.backend.category.dto;

import lombok.Builder;
import lombok.Getter;

public class CategoryDto {
    @Getter
    @Builder
    public static class Res{
        private Long memberId;
        private String name;
    }
}
