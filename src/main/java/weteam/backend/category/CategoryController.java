package weteam.backend.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weteam.backend.category.domain.Category;
import weteam.backend.category.dto.CategoryDto;
import weteam.backend.category.mapper.CategoryMapper;
import weteam.backend.hash_tag.domain.MemberHashtag;
import weteam.backend.hash_tag.dto.HashtagDto;
import weteam.backend.hash_tag.mapper.HashtagMapper;

import java.security.Principal;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category", description = "category API / jwt vlftn")
public class CategoryController {
    private final CategoryService categoryService;


    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "카테고리 생성, 반환값 없음 ")
    public void createCategory(@RequestBody @Valid CategoryDto.Create request, Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        Category category = CategoryMapper.instance.toEntity(request);
        categoryService.createCategory(category, memberId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('USER')")
    @Operation(summary = "사용자의 키테고리 전체 조회",
               responses = {
                       @ApiResponse(responseCode = "200",
                                    content = @Content(schema = @Schema(implementation = CategoryDto.ResList.class)))
               })
    public ResponseEntity<List<CategoryDto.Res>> findAllByMemberId(Principal principal) {
        Long memberId = Long.valueOf(principal.getName());
        List<Category> categoryList = categoryService.findAllByMemberId(memberId);
        return ResponseEntity.ok(CategoryMapper.instance.toResList(categoryList));
    }
}
