package org.mik.boardGamesCollector.repositories;

import org.mik.boardGamesCollector.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
