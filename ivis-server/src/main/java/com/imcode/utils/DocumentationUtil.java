package com.imcode.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.Permission;
import com.imcode.entities.superclasses.ContactInformation;
import com.imcode.search.SearchCriteries;
import com.imcode.services.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletContext;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by ruslan on 24.11.16.
 */
public class DocumentationUtil {

    private final String ZIP_FILE_NAME = "/api_docs.zip";
    private final String SERVICE_FOLDER = "services/";
    private final String ENTITY_FOLDER = "entities/";
    private final String INFO_PROPERTIES = "/service.info.properties";
    private Properties properties;
    private Map<String, List<Permission>> groups;
    private Class<JsonIgnore> JSON_IGNORE_ANNOTATION = JsonIgnore.class;

    private ZipOutputStream zipOutputStream;

    private Set<String> setOfUsedClasses = new HashSet<>();

    private String contactInformationType;

    public Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public void generate(PermissionService permissionService, ServletContext servletContext) {
        properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF" + INFO_PROPERTIES));
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
        }
        List<Permission> allPermissions = permissionService.findAll();
        groups = new HashMap<>();
        File file = new File(servletContext.getRealPath("/") + ZIP_FILE_NAME);

        if (file.exists()) {
            file.delete();
        }

        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e.getCause());
        }
        allPermissions.forEach(this::separateOnGroups);
        groups.forEach(this::process);
        generateRelatedInfo();
        try {
            zipOutputStream.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
        }
    }

    private void separateOnGroups(Permission permission) {
        String entityName = permission.getEntityName();
        List<Permission> list = groups.get(entityName);
        if (list == null) {
            List<Permission> permissions = new ArrayList<>();
            permissions.add(permission);
            groups.put(entityName, permissions);
        } else {
            list.add(permission);
        }
    }

    private void process(String entityName, List<Permission> permissions) {

        List<Permission> sortedByMethodName = permissions.stream()
                .sorted(Comparator.comparing(Permission::getMethodName))
                .collect(Collectors.toList());

        String inPlural = toPluralForm(entityName);

        StringBuilder content = new StringBuilder();

        content.append(inPlural)
                .append("\n")
                .append(generateStringFromRepeating('=', inPlural.length()))
                .append("\n")
                .append("\n")
                .append("``(implementation of ")
                .append(entityName)
                .append(" entity)``")
                .append("\n")
                .append("\n")
                .append("Provides following method for `API <http://docs.ivis.se/en/latest/api/index.html>`_ calls:")
                .append("\n")
                .append("\n")
                .append(generateMethodsNameList(sortedByMethodName))
                .append("\n")
                .append("\n")
                .append(generateMethodsInfo(sortedByMethodName));

        createZipEntry(SERVICE_FOLDER + toLowerCaseInPluralForm(entityName) + ".rst", content.toString());

    }

    private void createZipEntry(String name, String content) {
        ZipEntry entry = new ZipEntry(name);
        try {
            zipOutputStream.putNextEntry(entry);
            byte[] data = content.getBytes();
            zipOutputStream.write(data, 0, data.length);
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
        }
    }

    private void generateRelatedInfo() {
        Set<String> all = new HashSet<>();
        Set<String> current = new HashSet<>(setOfUsedClasses);
        while (setOfUsedClasses.size() > 0) {
            setOfUsedClasses = new HashSet<>();
            current.forEach(usedClass -> process(usedClass, all));
            current = new HashSet<>(setOfUsedClasses);
        }

    }

    private void process(String className, Set<String> all) {
        boolean add = all.add(className);
        if (add) {
            StringBuilder content = new StringBuilder();
            String simpleClassName = className.substring(className.lastIndexOf('.') + 1);

            Class<?> clazz = null;
            try {
                clazz = Class.forName(className.trim());
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage(), e.getCause());
            }
            assert clazz != null;
            String prefix = "List of properties from ";
            content
                    .append(prefix)
                    .append(simpleClassName)
                    .append('\n')
                    .append(generateStringFromRepeating('=', (prefix + simpleClassName).length()));
            if (clazz.isEnum()) {
                content.append('\n')
                        .append('\n')
                        .append("It is enum, that has STRING values.")
                        .append('\n')
                        .append('\n')
                        .append("Values:")
                        .append('\n')
                        .append("    ")
                        .append(genEnumList(clazz))
                        .append(".");
            } else {
                content.append(genListOfReturnType(className.trim()));
            }

            createZipEntry(ENTITY_FOLDER + simpleClassName + ".rst", content.toString());

        }
    }

    private String genEnumList(Class<?> clazz) {
        Object[] values = clazz.getEnumConstants();
        final String prefixAndSuffix = "\"";
        return Arrays.stream(values)
                .map(value -> {
                    try {
                        return (String) ReflectionUtils.invokeMethod(clazz.getMethod("name"), value);
                    } catch (NoSuchMethodException | ClassCastException e) {
                        logger.error(e.getMessage(), e.getCause());
                    }
                    return null;
                })
                .map(s -> prefixAndSuffix + s + prefixAndSuffix)
                .collect(Collectors.joining(", "));
    }

    private String generateMethodsNameList(List<Permission> permissions) {
        String prefix = "    * `";
        String suffix = "`_";
        return permissions.stream()
                .map(Permission::getMethodName)
                .map(methodName -> prefix + StringUtils.capitalize(methodName) + suffix)
                .collect(Collectors.joining("\n"));
    }

    private String generateMethodsInfo(List<Permission> sortedByMethodName) {
        return sortedByMethodName.stream()
                .map(this::genMethodInfo)
                .collect(Collectors.joining(""));
    }

    private String genMethodInfo(Permission permission) {

        String urlTitle = "URL:";
        String methodTitle = "Method:";
        String parametersRequestTitle = "Parameters request:";
        String parametersResponseTitle = "Parameters response:";

        StringBuilder methodInfo = new StringBuilder();
        String methodName = permission.getMethodName();
        String capitalize = StringUtils.capitalize(methodName);
        methodInfo.append(".. _`")
                .append(StringUtils.capitalize(methodName))
                .append("`:")
                .append("\n")
                .append("\n")
                .append(capitalize)
                .append("\n")
                .append(generateStringFromRepeating('-', capitalize.length()))
                .append("\n")
                .append("\n")
                .append(urlTitle)
                .append("\n")
                .append(generateStringFromRepeating('~', urlTitle.length()))
                .append("\n")
                .append("    *")
                .append(permission.getUrl())
                .append("*")
                .append("\n")
                .append("\n")
                .append(methodTitle)
                .append("\n")
                .append(generateStringFromRepeating('~', methodTitle.length()))
                .append("\n")
                .append("    *")
                .append(permission.getHttpMethod())
                .append("*")
                .append("\n")
                .append("\n")
                .append(parametersRequestTitle)
                .append("\n")
                .append(generateStringFromRepeating('~', parametersRequestTitle.length()))
                .append("\n")
                .append(resolveParams(permission.getParameters()))
                .append("\n")
                .append("\n")
                .append(parametersResponseTitle)
                .append("\n")
                .append(generateStringFromRepeating('~', parametersResponseTitle.length()))
                .append("\n")
                .append(resolveReturn(permission.getReturnValue()));

        String note = properties.getProperty(permission.getEntityName() + "." + permission.getMethodName());
        if (note != null) {
            methodInfo.append("\n")
                    .append(".. note::")
                    .append("\n")
                    .append("   ")
                    .append(note)
                    .append("\n")
                    .append("\n");
        }

        return methodInfo.toString();

    }

    private String resolveReturn(String returnValue) {

        StringBuilder responseInfo = new StringBuilder();
        return responseInfo.append("    *")
                .append(returnValue.substring(0, returnValue.lastIndexOf('<')))
                .append("*")
                .append("\n")
                .append("\n")
                .append("    Description:")
                .append("\n")
                .append(genListOfReturnType(returnValue.substring(returnValue.lastIndexOf('<') + 1, returnValue.length() - 1)))
                .append("\n")
                .toString();
    }

    private String genListOfReturnType(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
        }

        if (clazz == null) {
            return "        Starting downloading of file";
        } else if (Number.class.isAssignableFrom(clazz)) {
            return "        ARRAY<NUMBER>";
        }

        Map<String, String> fieldJsonType = new JsonFieldResolver(clazz).getFieldJsonType();
        StringBuilder fieldType = new StringBuilder("\n");
        fieldJsonType.forEach(
                (name, type) ->
                        fieldType.append("        #. ")
                                .append(name)
                                .append("(")
                                .append(mapType(type))
                                .append(")")
                                .append("\n")
        );
        return fieldType.toString();
    }

    private String resolveParams(String parameters) {
        if (parameters == null) {
            return "    *null*";
        }
        String[] split = parameters.split(";");
        if (split.length == 2) {
            return "    " + split[0] + "\n\n    " + mapType(split[1].substring(1)) + "";
        } else {
            return "    " + mapType(split[0]);
        }
    }

    private String mapType(String type) {
        if (!type.contains("<")) {
            return type;
        } else {
            String typeFullName = type.substring(type.lastIndexOf('<') + 1, type.lastIndexOf('>'));
            if (!typeFullName.contains(".")) {
                return typeFullName;
            }

            if (typeFullName.contains(",")) {
                String[] split = typeFullName.split(",");
                return "KEY_ENUM_OBJECT_PAIR<" + split[0].replace(split[0], wrapType(split[0])) + ", "
                        + split[1].replace(split[1], wrapType(split[1])).substring(1)
                        + ">";
            }

            //exclusion from rules
            Class<?> clazz = null;
            try {
                clazz = Class.forName(typeFullName);
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage(), e.getCause());
            }
            if (clazz != null) {
                if (clazz.equals(String.class)) {
                    return type.replace(String.class.getTypeName(), "STRING");
                } else if (clazz.equals(Class.class)){
                    return "STRING<ENTITY_CLASS_NAME>";
                } else if (Number.class.isAssignableFrom(clazz)) {
                    return "NUMBER";
                }
            }

            return type.replace(typeFullName, wrapType(typeFullName));
        }
    }

    private String wrapType(String typeFullName) {
        String prefix = "http://docs.ivis.se/en/latest/api/entities/";
        setOfUsedClasses.add(typeFullName);
        String simpleTypeName = typeFullName.substring(typeFullName.lastIndexOf('.') + 1);
        return  " `" + simpleTypeName + " <" + prefix + simpleTypeName + ".html>`_ ";
    }

    public static String toPluralForm(String word) {
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

    public static Set<Class<? extends Object>> getAllClassesFromPackage(String packageName) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        Set<BeanDefinition> classes = provider.findCandidateComponents(packageName);
        Set<Class<?>> allClasses = classes.stream()
                .map(bean -> {
                    try {
                        return Class.forName(bean.getBeanClassName());
                    } catch (ClassNotFoundException e) {
                        LoggerFactory.getLogger(DocumentationUtil.class).error(e.getMessage(), e.getCause());
                    }
                    return null;
                })
                .collect(Collectors.toSet());
        return allClasses;
    }

    private String generateStringFromRepeating(char ch, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    private String toLowerCaseInPluralForm(String str) {
        return toPluralForm(str.toLowerCase());
    }

    private class JsonFieldResolver {

        private Map<String, String> fieldJsonType = new LinkedHashMap<>();

        public JsonFieldResolver(Class<?> clazz) {
            if (ContactInformation.class.isAssignableFrom(clazz)) {
                contactInformationType = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            }
            ReflectionUtils.doWithFields(clazz, this::process);
        }

        public Map<String, String> getFieldJsonType() {
            return fieldJsonType;
        }

        private void process(Field field) {
            final String PERSISTENCE_PACKAGE_NAME = "javax.persistence";
            boolean fieldAnnotated = field.getAnnotation(JSON_IGNORE_ANNOTATION) != null;
            boolean getterAnnotated = false;
            Annotation[] annotations = field.getAnnotations();
            boolean isPersistenceField = Arrays.stream(annotations)
                    .anyMatch(annotation -> annotation.annotationType().getTypeName().startsWith(PERSISTENCE_PACKAGE_NAME));

            if (isPersistenceField) {
                try {
                    Method getter = field.getDeclaringClass().getMethod("get" + StringUtils.capitalize(field.getName()));
                    getterAnnotated = getter.getAnnotation(JSON_IGNORE_ANNOTATION) != null;
                } catch (NoSuchMethodException e) {
                    logger.error(e.getMessage(), e.getCause());
                }
            }

            if (!fieldAnnotated && !getterAnnotated && isPersistenceField) {
                fieldJsonType.put(toSnackCaseFromCamelCase(field.getName()), resolveReturnType(field));
            //exclusions
            } else if (field.getDeclaringClass().equals(SearchCriteries.SearchCriteriaResult.class)) {
                fieldJsonType.put(toSnackCaseFromCamelCase(field.getName()), resolveReturnType(field));
            }
        }

        private String resolveReturnType(Field field) {
            final String TYPE_NUMBER = "NUMBER";
            final String TYPE_STRING = "STRING";
            final String TYPE_BOOLEAN = "BOOLEAN";
            final String TYPE_KEY_VALUE_PAIR = "KEY_VALUE_PAIR";
            final String TYPE_ARRAY = "ARRAY";
            final String TYPE_OBJECT = "OBJECT";

            Class<?> type = field.getType();
            String typeName;
            String TYPE_COLLECTION_OR_OBJECT;
            if (type.equals(Serializable.class)) {
                return TYPE_NUMBER;
            } else if (type.equals(Date.class)) {
                return TYPE_NUMBER + "(Date representation wrapped)";
            } else if (Number.class.isAssignableFrom(type)) {
                return TYPE_NUMBER;
            } else if (type.equals(String.class)) {
                return TYPE_STRING;
            } else if (type.equals(Boolean.class)) {
                return TYPE_BOOLEAN;
            } else if (contactInformationType != null) {
                typeName = contactInformationType;
                TYPE_COLLECTION_OR_OBJECT = TYPE_OBJECT;
                contactInformationType = null;
            } else if (Map.class.isAssignableFrom(type)) {
                typeName = getTypeName(field, true);
                TYPE_COLLECTION_OR_OBJECT = TYPE_KEY_VALUE_PAIR;
            } else if (Collection.class.isAssignableFrom(type)) {
                typeName = getTypeName(field, false);
                TYPE_COLLECTION_OR_OBJECT = TYPE_ARRAY;
            } else {
                typeName = type.getTypeName();
                TYPE_COLLECTION_OR_OBJECT = TYPE_OBJECT;
            }
            return TYPE_COLLECTION_OR_OBJECT + "<" + typeName + ">";
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

        private String getTypeName(Field field, boolean isMap) {
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
            String typeName = genericType.getActualTypeArguments()[0].getTypeName();
            if (isMap) {
                typeName += ", " + genericType.getActualTypeArguments()[1].getTypeName();
            }
            return typeName;
        }
    }

}
