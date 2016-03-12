package com.imcode.entities;

import javax.persistence.AttributeConverter;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vitaly on 11.03.16.
 */
public class ListToStringConverter1 implements AttributeConverter<List, String> {
    //        private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List attribute) {
//            try {
        return null;
//                return mapper.writeValueAsString(attribute);
//            } catch (JsonProcessingException e) {
//                return null;
//            }
    }

    @Override
    public List convertToEntityAttribute(String dbData) {
//            try {
        return null;
//                return mapper.readValue(dbData, List.class);
//            } catch (IOException e) {
//                return new ArrayList<>();
//            }
    }
}
