package com.imcode.repositories;

import com.imcode.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruslan on 5/10/16.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
