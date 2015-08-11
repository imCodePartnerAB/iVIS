package com.imcode.controllers.html.tiles.preparers;

import com.imcode.entities.interfaces.JpaEntity;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.ListAttribute;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.*;

/**
 * Created by vitaly on 10.07.15.
 */
@Component
public class EntityListPreparer extends ViewPreparerSupport {
    private static final String DEFAULT_COLUMN_NAMES = "id,name";
    //    @Autowired
//    private EntityManager entityManager;

//    @Value("${ServerAddress}")
//    private String serverAddress;

    @Override
    public void execute(TilesRequestContext tilesContext, AttributeContext attributeContext) {
        Map<String, Object> requestAttributes = tilesContext.getRequestScope();
        String entityListTitle = (String) requestAttributes.get("entityListTitle");
        List<JpaEntity> entities = (List<JpaEntity>) requestAttributes.get("entities");
        Map<String, Attribute> aditionalAttributes = new HashMap<>();
        String entityClassName = (String) requestAttributes.get("entityClassName");
        String columnNames = (String) requestAttributes.get("columnNames");

        if (entityListTitle == null && entities != null) {
            Iterator iterator = entities.iterator();

            if (entityClassName != null) {
                entityListTitle = entityClassName;
            } else if (iterator.hasNext()) {
                JpaEntity entity = (JpaEntity) iterator.next();
                entityListTitle = entity.getClassDescription();
            } else {
                entityListTitle = "Entity";
            }

            entityListTitle += " list";

//            aditionalAttributes.put("entityListTitle", new Attribute(entityListTitle));
            attributeContext.putAttribute("entityListTitle", new Attribute(entityListTitle), true);
        }

        if (columnNames == null || !(columnNames instanceof String) || columnNames.isEmpty()) {
            attributeContext.putAttribute("columnNames", new Attribute(DEFAULT_COLUMN_NAMES), true);
        }

//        attributeContext.addMissing(aditionalAttributes);


//        List<Pair<String, String>> menuMap = new LinkedList<>();
//        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
//
//        for (EntityType entityType : entityTypes) {
//            String entityClassName = entityType.getJavaType().getSimpleName();
//            menuMap.add(new ImmutablePair<>(entityClassName, "/domain/" + entityClassName));
//        }
//
//
//        attributeContext.putAttribute("menuMap", new ListAttribute(menuMap), true);
    }

//    class MenuPair {
//
//    }
}
