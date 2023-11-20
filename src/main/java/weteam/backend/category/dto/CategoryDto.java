package weteam.backend.category.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    @Getter
    @Builder
    public static class Create{
        private String name;
        private String color;
    }
    @Getter
    @Builder
    public static class Res{
        private Long id;
        private String name;
        private String color;
    }
    public static class ResList{
        List<Res> resList = new ArrayList<>();
    }
}
