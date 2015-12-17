package com.imcode.services;

import com.imcode.entities.interfaces.JpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vitaly on 16.12.15.
 */
@Deprecated
@Service
@Transactional
@PersistenceContext
public class TestService {
    @Autowired
    private EntityManager em;

    public void persist(List<JpaEntity<Long>> entities) {
        List<JpaEntity<Long>> margedEntities = new ArrayList<>();

        for (JpaEntity<Long> entity : entities) {
            margedEntities.add(em.merge(entity));
        }

        em.flush();
    }
}
