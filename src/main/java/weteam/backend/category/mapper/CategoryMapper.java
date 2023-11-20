package weteam.backend.category.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import weteam.backend.category.domain.Category;
import weteam.backend.category.dto.CategoryDto;
import weteam.backend.member.mapper.MemberMapper;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryMapper instance = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryDto.Create request);

    @Named("E2D")
    CategoryDto.Res toRes(Category category);
    @IterableMapping(qualifiedByName = "E2D")
    List<CategoryDto.Res> toResList(List<Category> categoryList);
}
