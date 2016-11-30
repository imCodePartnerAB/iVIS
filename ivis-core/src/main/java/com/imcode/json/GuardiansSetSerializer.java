package com.imcode.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.imcode.entities.Guardian;
import com.imcode.entities.Pupil;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ruslan on 04.06.16.
 */
public class GuardiansSetSerializer extends JsonSerializer<Set<Guardian>> {

    @Override
    public void serialize(Set<Guardian> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        value.stream()
                .peek(guardian -> {
                    Set<Pupil> pupils = guardian.getPupils();
                    pupils = pupils.stream().peek(pupil -> pupil.setGuardians(null)).collect(Collectors.toSet());
                    guardian.setPupils(pupils);
                })
                .forEach(guardian -> {
                    try {
                        jgen.writeObject(guardian);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        jgen.writeEndArray();
    }
}
