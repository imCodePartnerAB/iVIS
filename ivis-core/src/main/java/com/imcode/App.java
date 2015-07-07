package com.imcode;

import com.imcode.entities.embed.AfterSchoolCenterSchema;

import java.time.DayOfWeek;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        HashSet<Role> roles = new HashSet<>();
//        roles.add(new Role(1L, "ADF"));
//        roles.add(new Role(2L, "GFGD"));
//        roles.add(new Role(3L, "HHH"));
//        byte[] bytes = SerializationUtils.serialize(roles);
//        HashSet<Role> role1 = SerializationUtils.deserialize(bytes);
//        System.out.println(role1);
//        System.out.println("Hello World!");
        Set<AfterSchoolCenterSchema> pupilSchoolCenterSchema = new LinkedHashSet<AfterSchoolCenterSchema>(5);
        Set<AfterSchoolCenterSchema> afterSchoolCenterSchema = new LinkedHashSet<AfterSchoolCenterSchema>(5);

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {//todo
            AfterSchoolCenterSchema schema = new AfterSchoolCenterSchema(null, dayOfWeek);

            for (AfterSchoolCenterSchema centerSchema : pupilSchoolCenterSchema) {
                if (dayOfWeek.equals(centerSchema.getDayOfWeek())) {
                    schema = centerSchema;
                    break;
                }
            }

            afterSchoolCenterSchema.add(schema);
        }
        System.out.println(afterSchoolCenterSchema);
    }
}
