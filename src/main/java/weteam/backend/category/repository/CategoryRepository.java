package weteam.backend.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weteam.backend.category.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
