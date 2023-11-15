package weteam.backend.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CategoryResponse {
    private Long memberId;
    private String name;
}
