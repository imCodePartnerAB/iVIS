package com.imcode.services;

import java.util.List;

/**
 * Created by ruslan on 5/12/16.
 */
public interface SearchCriteriaService<T> {
    List<T> findBySearchCriteria (String searchText, String orderBy);
}
