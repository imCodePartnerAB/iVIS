package com.imcode.misc;

import com.imcode.misc.errors.*;
import com.imcode.misc.errors.Error;

/**
 * Created by vitaly on 26.02.15.
 */
public class ErrorResponse extends Response {
    private com.imcode.misc.errors.Error error;

    public ErrorResponse() {
    }

    public ErrorResponse(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
