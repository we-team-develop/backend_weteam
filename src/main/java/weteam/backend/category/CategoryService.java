package weteam.backend.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weteam.backend.category.domain.Category;
import weteam.backend.category.dto.CategoryDto;
import weteam.backend.category.mapper.CategoryMapper;
import weteam.backend.category.repository.CategoryRepository;
import weteam.backend.member.MemberService;
import weteam.backend.member.domain.Member;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MemberService memberService;

    public void createCategory(CategoryDto request, Long memberId) {
        Member member = memberService.loadMemberById(memberId);
        Category category = CategoryMapper.instance.toEntity(request, member);

        if (findByName(request.getName()).isPresent()) {
            throw new RuntimeException("이미 작성된 카테고리");
        } else {
            categoryRepository.save(category);
        }
    }
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
    public List<Category> findAllByMemberId(Long memberId) {
        return categoryRepository.findAllByMemberId(memberId);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
    public Category loadCategory(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("없는 카테고리"));
    }

    public void updateCategory(Long categoryId, CategoryDto request, Long memberId) {
        Category data = loadCategory(categoryId);
        if (!Objects.equals(data.getMember().getId(), memberId)) {
            throw new RuntimeException("다른 사용자의 카테고리");
        }
        Category category = CategoryMapper.instance.toEntity(request, data.getMember());
        category.setId(categoryId);
        categoryRepository.save(category);
    }
}
