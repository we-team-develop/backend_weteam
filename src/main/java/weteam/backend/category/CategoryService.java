package weteam.backend.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.category.domain.Category;
import weteam.backend.category.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(Category request,Long memberId) {
        if (findByName(request.getName()).isPresent()) {
            throw new RuntimeException("이미 작성된 카테고리");
        } else {
            categoryRepository.save(request);
        }
    }
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
    public List<Category> findAllByMemberId(Long memberId) {
        return categoryRepository.findAllByMemberId(memberId);
    }
}
