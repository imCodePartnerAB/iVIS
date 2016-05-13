package com.imcode.controllers.restful;

import com.imcode.controllers.AbstractRestController;
import com.imcode.entities.Category;
import com.imcode.services.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ruslan on 5/10/16.
 */
@RestController
@RequestMapping("/v1/{format}/categories")
public class CategoryRestControllerImpl extends AbstractRestController<Category, Long, CategoryService> {
}
