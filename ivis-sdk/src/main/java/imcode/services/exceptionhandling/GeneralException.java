package imcode.services.exceptionhandling;

import java.util.List;

/**
 * Created by ruslan on 19.08.16.
 */
public class GeneralException extends RuntimeException {

    private Integer errorCode;

    private String errorMessage;

    private List<String> errorDescription;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(List<String> errorDescription) {
        this.errorDescription = errorDescription;
    }


    // Error codes
    public static final int VALIDATION_EC = 1001;

    public static final int DATABASE_PERSISTENCE_EC = 2001;

    public static final int JSON_MAPPING_EC = 3001;

    public static final int UNCAUGHT_EC = 4001;

    public static final int SECURITY_EC = 5001;
}
