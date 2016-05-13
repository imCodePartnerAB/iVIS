package com.imcode.services.jpa;

import com.imcode.entities.Category;
import com.imcode.repositories.CategoryRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.CategoryService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 5/10/16.
 */
@Service
public class CategoryRepoImpl extends AbstractService<Category, Long, CategoryRepository> implements CategoryService {
}
