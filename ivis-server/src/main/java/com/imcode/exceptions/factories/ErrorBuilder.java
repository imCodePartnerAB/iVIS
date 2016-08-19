package com.imcode.exceptions.factories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.imcode.exceptions.wrappers.GeneralError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;

import javax.persistence.PersistenceException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 19.08.16.
 */
public class ErrorBuilder {

    public static GeneralError buildValidationError(Errors errors) {

        GeneralError generalError = new GeneralError();

        generalError.setErrorCode(GeneralError.VALIDATION_EC);

        int errorCount = errors.getErrorCount();
        generalError.setErrorMessage("Validation failed. " + errorCount + " error" + (errorCount > 1 ? "s" : ""));

        List<String> errorDescription = errors.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toCollection(LinkedList::new));
        generalError.setErrorDescription(errorDescription);

        return generalError;
    }

    public static GeneralError buildDatabasePersistenceError(PersistenceException persistenceException) {

        GeneralError generalError = new GeneralError();

        generalError.setErrorCode(GeneralError.DATABASE_PERSISTENCE_EC);

        generalError.setErrorMessage("Database persistence error");

        String cause = persistenceException.getCause().toString();

        cause = cause.replaceAll("javax\\.persistence\\.PersistenceException: org\\.hibernate\\.exception\\.", "");

        List<String> errorDescription = Arrays.asList(cause);
        generalError.setErrorDescription(errorDescription);

        return generalError;

    }

    public static GeneralError buildJsonMappingException(JsonMappingException e) {

        GeneralError generalError = new GeneralError();

        generalError.setErrorCode(GeneralError.JSON_MAPPING_EC);

        generalError.setErrorMessage("JSON Mapping error");

        String cause = e.getCause().toString();

        List<String> errorDescription = Arrays.asList(cause);
        generalError.setErrorDescription(errorDescription);

        return generalError;

    }

    public static GeneralError buildUncaughtException(Exception e) {

        GeneralError generalError = new GeneralError();

        generalError.setErrorCode(GeneralError.UNCAUGHT_EC);

        generalError.setErrorMessage("Uncaught error");

        String cause = e.getCause().toString();

        List<String> errorDescription = Arrays.asList(cause);
        generalError.setErrorDescription(errorDescription);

        return generalError;

    }

}
