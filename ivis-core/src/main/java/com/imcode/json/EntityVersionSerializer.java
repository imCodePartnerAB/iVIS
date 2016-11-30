package com.imcode.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.imcode.entities.EntityVersion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ruslan on 28.07.16.
 */
public class EntityVersionSerializer extends JsonSerializer<EntityVersion> {

    @Override
    public void serialize(EntityVersion value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Map<String, Object> entityVersion = new HashMap<>();
        entityVersion.put("id", value.getId());
        entityVersion.put("entity_id", value.getEntityId());
        entityVersion.put("entity", value.getEntity());
        entityVersion.put("entity_class", value.getEntityClass());
        entityVersion.put("timestamp", value.getTimestamp());
        jgen.writeObject(entityVersion);
    }
}
