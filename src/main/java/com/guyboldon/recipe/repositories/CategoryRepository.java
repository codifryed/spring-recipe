package com.guyboldon.recipe.repositories;

import com.guyboldon.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
