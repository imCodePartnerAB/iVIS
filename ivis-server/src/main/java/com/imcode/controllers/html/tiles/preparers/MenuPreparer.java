package com.imcode.controllers.html.tiles.preparers;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.interfaces.JpaNamedEntity;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.ListAttribute;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.*;

/**
 * Created by vitaly on 10.07.15.
 */
@Component
public class MenuPreparer extends ViewPreparerSupport {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ApplicationContext context;

    private Repositories repositories;

//    @Value("${ServerAddress}")
//    private String serverAddress;

    @Override
    public void execute(TilesRequestContext tilesContext, AttributeContext attributeContext) {

        List<Pair<String, String>> menuMap = new LinkedList<>();
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        for (EntityType entityType : entityTypes) {
            Class entityClass = entityType.getJavaType();
            String entityClassName = entityClass.getSimpleName();

            if (JpaEntity.class.isAssignableFrom(entityClass) && repositories.hasRepositoryFor(entityClass)) {
                menuMap.add(new ImmutablePair<>(entityClassName, "/domain/" + entityClassName));
            }

            int i = 0;
        }

        attributeContext.putAttribute("menuMap", new ListAttribute(menuMap), true);
    }


    @PostConstruct
    public void init() {
        repositories = new Repositories(context);
    }

    class MenuPair {

    }
}
