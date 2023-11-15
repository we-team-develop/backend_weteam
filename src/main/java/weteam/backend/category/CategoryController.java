package weteam.backend.category;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "category API")
public class CategoryController {
    private final CategoryService categoryService;
}
