package com.imcode.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.imcode.entities.EntityVersion;
import com.imcode.entities.Role;
import com.imcode.entities.User;
import com.imcode.entities.interfaces.JpaEntity;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by vitaly on 01.03.16.
 */
public class EntityVersionDeserializer extends JsonDeserializer<EntityVersion> {
    @Override
    public EntityVersion deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        EntityVersion result = new EntityVersion();
        JsonNode node = jp.getCodec().readTree(jp);
        Long id = node.get("id").longValue();
        Long entityId = node.get("entity_id").longValue();
        String entityClassName = node.get("entity_class").asText();
        Date timestamp = new Date((node.get("timestamp").asLong()));
        Class<?> entityClass = null;
        JpaEntity<?> entity = null;
        try {
            entityClass = Class.forName(entityClassName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            entity = (JpaEntity<?>) mapper.readValue(node.get("entity").traverse(), entityClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        result.setId(id);
        result.setEntityClass(entityClass);
        result.setEntityId(entityId);
        result.setTimestamp(timestamp);
        result.setEntity(entity);


        return result;
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("admin", "pass", new Role("ROLE_ADMIN"));
        EntityVersion result = new EntityVersion(user);
        String versionString = mapper.writeValueAsString(result);
        System.out.println(versionString);
        EntityVersion entityVersion = mapper.readValue(versionString, EntityVersion.class);
        System.out.println(entityVersion);
    }
}
