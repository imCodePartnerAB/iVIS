package com.imcode.exceptions.factories;

import com.imcode.exceptions.wrappers.GeneralError;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;

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

    public static GeneralError buildValidationError(List<String> description) {

        GeneralError generalError = new GeneralError();

        generalError.setErrorCode(GeneralError.VALIDATION_EC);

        generalError.setErrorMessage("Validation failed. 1 error");

        generalError.setErrorDescription(description);

        return generalError;
    }

    public static GeneralError buildDatabasePersistenceError(Exception e) {

        return buildException(e, GeneralError.DATABASE_PERSISTENCE_EC);

    }

    public static GeneralError buildJsonMappingException(Exception e) {

        return buildException(e, GeneralError.JSON_XML_MAPPING_EC);

    }

    public static GeneralError buildUncaughtException(Exception e) {

        return buildException(e, GeneralError.UNCAUGHT_EC);

    }

    public static GeneralError buildSecurityException(String msg) {
        GeneralError generalError = new GeneralError();
        generalError.setErrorCode(GeneralError.SECURITY_EC);
        generalError.setErrorMessage("Security level error");
        generalError.setErrorDescription(Arrays.asList(msg));
        return generalError;
    }

    private static GeneralError buildException(Exception e, int errorCode) {

        GeneralError generalError = new GeneralError();

        generalError.setErrorCode(errorCode);

        String message = null;

        switch (errorCode) {

            case GeneralError.DATABASE_PERSISTENCE_EC:
                message = "Database level error";
                break;

            case GeneralError.JSON_XML_MAPPING_EC:
                message = "JSON level error";
                break;

            case GeneralError.UNCAUGHT_EC:
                message = "Uncaught error";
                break;

        }

        generalError.setErrorMessage(message);

        Throwable cause = e.getCause();
        String causeString = cause == null ? e.getClass().getSimpleName() : ExceptionUtils.getRootCauseMessage(e);
        String messag = e.getMessage();
        String msg = messag == null ? e.getClass().getSimpleName() : ExceptionUtils.getMessage(e);

        List<String> errorDescription = Arrays.asList(causeString, msg);
        generalError.setErrorDescription(errorDescription);

        return generalError;

    }

}
