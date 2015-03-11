package com.imcode.controllers;

import com.imcode.misc.ErrorResponse;
import com.imcode.misc.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

/**
 * Created by vitaly on 27.02.15.
 */
//@ControllerAdvice
public class ExceptionHandlerControllerImpl {
//    @Autowired
//    private ErrorFactory errorFactory;
//
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    Object exceptionHandler(Exception e, WebRequest webRequest) {
//
//        com.imcode.misc.errors.Error error = errorFactory.getErrorWithDescription(5);
//        error.setrequestParams(webRequest.getParameterMap());
//        return new ErrorResponse(error);
//    }
//
//    public ErrorFactory getErrorFactory() {
//        return errorFactory;
//    }
//
//    public void setErrorFactory(ErrorFactory errorFactory) {
//        this.errorFactory = errorFactory;
//    }
}
