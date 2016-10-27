package com.imcode.controllers.html;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.imcode.entities.EntityRestProviderInformation;
import com.imcode.entities.MethodRestProviderForEntity;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.EntityRestProviderInformationService;
import com.imcode.utils.ControllerReflectionUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Ref;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.function.Consumer;

import static org.apache.commons.lang.StringUtils.uncapitalize;

/**
 * Created by ruslan on 25.10.16.
 */
@Controller
public class DocumentationGenerator {

    @Autowired
    private EntityRestProviderInformationService infoService;

    @Autowired
    private ServletContext servletContext;

    private final String url = "http://docs.ivis.se/en/latest/api/";

    @RequestMapping(value = "/generatedocs", method = RequestMethod.GET)
    public ModelAndView generate() {

        List<EntityRestProviderInformation> allInfo = infoService.findAll();

        Consumer<EntityRestProviderInformation> generateFileDoc =
                this::generateFileDoc;

        Set<Class<?>> enttityClasses = ControllerReflectionUtil
                .getAllClassesFromPackage(ControllerReflectionUtil.ENTITY_PACKAGE);

        enttityClasses.forEach(clazz -> {
            try {
                String pathToFolder = servletContext.getRealPath("/docs_ivis_se/");

                String simpleName = clazz.getSimpleName();
                String fileName = pathToFolder + "/entities/" + simpleName.toLowerCase() + ".rst";
                File file = new File(fileName);
                if (!file.exists()) file.createNewFile();
                PrintWriter pw = new PrintWriter(file);
                pw.println("List of properties from " + simpleName);
                pw.println("========================" + generateStringFromRepeating('=', simpleName.length()));
                pw.println();
                generateProperties(pw, simpleName);

                pw.flush();
                pw.close();

            } catch (Exception e) {

            }

        });


        allInfo.forEach(generateFileDoc);

        return new ModelAndView("redirect:/");
    }

    private void generateFileDoc(EntityRestProviderInformation info) {

        try {
            String entityClass = info.getEntityClass();
            String pathToFolder = servletContext.getRealPath("/docs_ivis_se/");

            String fileName = pathToFolder + "/" + toLowerCaseInPluralForm(entityClass) + ".rst";
            File file = new File(fileName);

            if (!file.exists()) file.createNewFile();

            Set<MethodRestProviderForEntity> entityProviderMethods = info.getEntityProviderMethods();

            PrintWriter pw = new PrintWriter(file);

            String pluralForm = toPluralForm(entityClass);

            pw.println(pluralForm);
            pw.println(generateStringFromRepeating('=', pluralForm.length()));
            pw.println();
            pw.println("``(implementation of " + entityClass + " entity)``");
            pw.println();
            pw.println("Provides following method for `API <index.html>`_ calls:");
            pw.println();
            pw.println("    * `Get " + uncapitalize(entityClass) + "`_");
            pw.println("    * `Get " + uncapitalize(pluralForm) + "`_");
            pw.println("    * `Save " + uncapitalize(entityClass) + "`_");
            pw.println("    * `Save " + uncapitalize(pluralForm) + "`_");
            pw.println("    * `Update " + uncapitalize(entityClass) + "`_");
            pw.println("    * `Delete " + uncapitalize(entityClass) + "`_");
            pw.println("    * `Get " + uncapitalize(entityClass) + " or " +  uncapitalize(pluralForm) + " by personal id`_");
            pw.println("    * `Get " + uncapitalize(entityClass) + " or " +  uncapitalize(pluralForm) + " by name`_");
            pw.println();

            pw.println(".. _`Get " + uncapitalize(entityClass) + "`:");
            pw.println();
            pw.println("Get " + uncapitalize(entityClass));
            pw.println("----" + generateStringFromRepeating('-', uncapitalize(entityClass).length()));
            pw.println();
            pw.println("URL:");
            pw.println("~~~~");
            pw.println("    */" + toLowerCaseInPluralForm(entityClass) + "/{id}*");
            pw.println();
            pw.println("Method:");
            pw.println("~~~~~~~");
            pw.println("    *GET*");
            pw.println();
            pw.println("Parameters request:");
            pw.println("~~~~~~~~~~~~~~~~~~~");
            pw.println("    *null*");
            pw.println();
            pw.println("Parameters response:");
            pw.println("~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *Object*");
            pw.println();
            pw.println("    *With properties:*");

            generateProperties(pw, entityClass);
            pw.println();

            pw.println(".. _`Get " + toLowerCaseInPluralForm(entityClass) + "`:");
            pw.println();
            pw.println("Get " + toLowerCaseInPluralForm(entityClass));
            pw.println("----" + generateStringFromRepeating('-', toLowerCaseInPluralForm(entityClass).length()));
            pw.println();
            pw.println("URL:");
            pw.println("~~~~");
            pw.println("    */" + toLowerCaseInPluralForm(entityClass) + "*");
            pw.println();
            pw.println("Method:");
            pw.println("~~~~~~~");
            pw.println("    *GET*");
            pw.println();
            pw.println("Parameters request:");
            pw.println("~~~~~~~~~~~~~~~~~~~");
            pw.println("    *null*");
            pw.println();
            pw.println("Parameters response:");
            pw.println("~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *Array*");
            pw.println();
            pw.println(".. seealso::");
            pw.println();
            pw.println("    Array consists of objects from `Get " + uncapitalize(entityClass) + "`_ method");
            pw.println();

//            pw.println(".. _`Save " + uncapitalize(entityClass) + "`:");
//            pw.println();
            pw.println("Save " + uncapitalize(entityClass));
            pw.println("-----" + generateStringFromRepeating('-', uncapitalize(entityClass).length()));
            pw.println();
            pw.println("URL:");
            pw.println("~~~~");
            pw.println("    */" + toLowerCaseInPluralForm(entityClass) + "*");
            pw.println();
            pw.println("Method:");
            pw.println("~~~~~~~");
            pw.println("    *POST*");
            pw.println();
            pw.println("Parameters request:");
            pw.println("~~~~~~~~~~~~~~~~~~~");
            pw.println("    *OBJECT(" + entityClass +")*");
            pw.println();
            pw.println("Parameters response:");
            pw.println("~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *OBJECT(" + entityClass +")*");
            pw.println();
            pw.println("Null properties:");
            pw.println("~~~~~~~~~~~~~~~~");
            pw.println("    *id*");
            pw.println();

//            pw.println(".. _`Save " + toLowerCaseInPluralForm(entityClass) + "`:");
//            pw.println();
            pw.println("Save " + toLowerCaseInPluralForm(entityClass));
            pw.println("-----" + generateStringFromRepeating('-', toLowerCaseInPluralForm(entityClass).length()));
            pw.println();
            pw.println("URL:");
            pw.println("~~~~");
            pw.println("    */" + toLowerCaseInPluralForm(entityClass) + "*");
            pw.println();
            pw.println("Method:");
            pw.println("~~~~~~~");
            pw.println("    *POST*");
            pw.println();
            pw.println("Parameters request:");
            pw.println("~~~~~~~~~~~~~~~~~~~");
            pw.println("    *Array(" + entityClass +")*");
            pw.println();
            pw.println("Parameters response:");
            pw.println("~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *Array(" + entityClass +")*");
            pw.println("Null properties of every object in array:");
            pw.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *id*");
            pw.println();

            pw.println(".. _`Update " + uncapitalize(entityClass) + "`:");
            pw.println();
            pw.println("Update " + uncapitalize(entityClass));
            pw.println("-------" + generateStringFromRepeating('-', uncapitalize(entityClass).length()));
            pw.println();
            pw.println("URL:");
            pw.println("~~~~");
            pw.println("    */" + toLowerCaseInPluralForm(entityClass)  + "/{id}*");
            pw.println();
            pw.println("Method:");
            pw.println("~~~~~~~");
            pw.println("    *PUT*");
            pw.println();
            pw.println("Parameters request:");
            pw.println("~~~~~~~~~~~~~~~~~~~");
            pw.println("    *OBJECT(" + entityClass +")*");
            pw.println();
            pw.println("Parameters response:");
            pw.println("~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *OBJECT(" + entityClass +")*");
            pw.println();
            pw.println(".. note::");
            pw.println("    ");
            pw.println("    property will be updated, if you don't want update property it need set null");
            pw.println();

            pw.println(".. _`Delete " + uncapitalize(entityClass) + "`:");
            pw.println();
            pw.println("Delete " + uncapitalize(entityClass));
            pw.println("-------" + generateStringFromRepeating('-', uncapitalize(entityClass).length()));
            pw.println();
            pw.println("URL:");
            pw.println("~~~~");
            pw.println("    */" + toLowerCaseInPluralForm(entityClass)  + "/{id}*");
            pw.println();
            pw.println("Method:");
            pw.println("~~~~~~~");
            pw.println("    *DELETE*");
            pw.println();
            pw.println("Parameters request:");
            pw.println("~~~~~~~~~~~~~~~~~~~");
            pw.println("    *null*");
            pw.println();
            pw.println("Parameters response:");
            pw.println("~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *OBJECT(" + entityClass +")*");
            pw.println();
            pw.println(".. note::");
            pw.println();
            pw.println("    you receive deleted object");
            pw.println();

            pw.println(".. _`Get " + uncapitalize(entityClass) + " or " +  uncapitalize(pluralForm) + " by personal id`:");
            pw.println();
            pw.println("Get " + uncapitalize(entityClass) + " or " +  uncapitalize(pluralForm) + " by personal id");
            pw.println("----" + generateStringFromRepeating('-', ((uncapitalize(entityClass) + " or " +  uncapitalize(pluralForm) + " personal id")).length()));
            pw.println();
            pw.println("URL:");
            pw.println("~~~~");
            pw.println("    */" + toLowerCaseInPluralForm(entityClass) + "*");
            pw.println();
            pw.println("Method:");
            pw.println("~~~~~~~");
            pw.println("    *GET*");
            pw.println();
            pw.println("Parameters request:");
            pw.println("~~~~~~~~~~~~~~~~~~~");
            pw.println("    *personalId(STRING)*");
            pw.println("    and optional *first(BOOLEAN)*");
            pw.println();
            pw.println("Parameters response:");
            pw.println("~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *ARRAY or OBJECT (" + entityClass +")*");
            pw.println();

            pw.println(".. _`Get " + uncapitalize(entityClass) + " or " +  uncapitalize(pluralForm) + " by name`:");
            pw.println();
            pw.println("Get " + uncapitalize(entityClass) + " or " +  uncapitalize(pluralForm) + " by name");
            pw.println("----" + generateStringFromRepeating('-', (uncapitalize(entityClass) + " or " +  uncapitalize(pluralForm) + " by name").length()));
            pw.println();
            pw.println("URL:");
            pw.println("~~~~");
            pw.println("    */" + toLowerCaseInPluralForm(entityClass) + "*");
            pw.println();
            pw.println("Method:");
            pw.println("~~~~~~~");
            pw.println("    *GET*");
            pw.println();
            pw.println("Parameters request:");
            pw.println("~~~~~~~~~~~~~~~~~~~");
            pw.println("    *name(STRING)*");
            pw.println("    and optional *first(BOOLEAN)*");
            pw.println();
            pw.println("Parameters response:");
            pw.println("~~~~~~~~~~~~~~~~~~~~");
            pw.println("    *ARRAY or OBJECT (" + entityClass +")*");
            pw.println();


            pw.flush();
            pw.close();



        } catch (Exception e) {

        }


    }

    private void generateProperties(PrintWriter pw, String entityClass) throws Exception {

        Class<?> clazz = Class.forName("com.imcode.entities." + entityClass);
        Object instance = clazz.newInstance();
        Map<String, String> nameTypePairs = new HashMap<>();
        ReflectionUtils.doWithFields(clazz, field -> {
            try {
                ReflectionUtils.makeAccessible(field);
                Class<?> type = field.getType();
                if (field.getName().equals("id")) {
                    field.set(instance, new Long(0));
                } else {
                    if (type.equals(String.class)) {
                        field.set(instance, "");
                    }
                    if (type.equals(Date.class)) {
                        field.set(instance, new Date());
                    }
                    if (type.equals(Long.class)) {
                        field.set(instance, new Long(0));
                    }
                    if (type.equals(Integer.class)) {
                        field.set(instance, new Integer(0));
                    }
                    if (Collection.class.isAssignableFrom(type)) {
                        try {
                            field.set(instance, type.newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                        nameTypePairs.put(toSnackCaseFromCamelCase(field.getName()), field.getName().toLowerCase());
                    }
                    if (type.equals(Boolean.class)) {
                        field.set(instance, false);
                    }

                    if (JpaEntity.class.isAssignableFrom(type)) {
                        try {
                            field.set(instance, type.newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                        nameTypePairs.put(toSnackCaseFromCamelCase(field.getName()), type.getTypeName().substring(type.getTypeName().lastIndexOf('.') + 1));
                    }
                }
            } catch (Exception ee) {

            }


        });


        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        String json = mapper.writeValueAsString(instance);

        ObjectNode jsonNodes = new ObjectMapper().readValue(json, ObjectNode.class);

        Iterator<String> fields = jsonNodes.fieldNames();

        fields.forEachRemaining(s -> {

            try {
                String type = jsonNodes.get(s).getNodeType().toString();
                pw.println("        #. " + s + "(" + type + ")");
                if (type.equals("OBJECT")) {
                    String simpleName = nameTypePairs.get(s);
                    pw.println("           " + "type of `" + simpleName + " <" + url + simpleName.toLowerCase() + ".html>`_");
                }
                if (type.equals("ARRAY")) {
                    String simpleName = nameTypePairs.get(s);
                    String capitalize = org.springframework.util.StringUtils.capitalize(simpleName);
                    pw.println("           " + "type of `" + capitalize.substring(0, capitalize.length() - 1) + " <" + url + simpleName.substring(0, simpleName.length() - 1) + ".html>`_");
                }
            } catch (Exception e) {

            }

        });
        pw.println();
        pw.println("Example of response:");
        pw.println("~~~~~~~~~~~~~~~~~~~~");
        pw.println();
        pw.println(".. code-block:: json");
        pw.print("\n    ");
        pw.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(instance).replaceAll("\n", "\n    "));
    }


    private String toLowerCaseInPluralForm(String str) {
        return toPluralForm(str.toLowerCase());
    }

    private String toPluralForm(String word) {
        StringBuilder modifiedEntityName = new StringBuilder(word);
        char lastLater = modifiedEntityName.charAt(modifiedEntityName.length() - 1);
        switch (lastLater) {
            case 'y': {
                modifiedEntityName.deleteCharAt(modifiedEntityName.length() - 1).append("ies");
                break;
            }

            case 's': {
                modifiedEntityName.append("es");
                break;
            }

            default:
                modifiedEntityName.append('s');
        }
        return modifiedEntityName.toString();
    }

    private String generateStringFromRepeating(char ch, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    private String toSnackCaseFromCamelCase(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                stringBuilder.append('_');
                stringBuilder.append(Character.toLowerCase(ch));
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }


    private String splitWithSpacingAndCapitalize(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb.append(Character.isUpperCase(c) ? " " + Character.toLowerCase(c) : i == 0 ? Character.toUpperCase(c) + "" : c + "");
        }
        return sb.toString();
    }


}
