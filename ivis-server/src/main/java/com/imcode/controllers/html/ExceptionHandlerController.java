package com.imcode.controllers.html;

import com.imcode.exceptions.factories.ErrorBuilder;
import com.imcode.exceptions.wrappers.GeneralError;
import com.imcode.misc.ErrorResponse;
import com.imcode.misc.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * Created by vitaly on 27.02.15.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(Exception exception) {


        GeneralError generalError = null;

        if (exception instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
            generalError = ErrorBuilder.buildValidationError(bindingResult);
        } else if (exception instanceof BindException) {
            BindingResult bindingResult = ((BindException) exception).getBindingResult();
            generalError = ErrorBuilder.buildValidationError(bindingResult);
        }else if (exception instanceof DataAccessException) {
            generalError = ErrorBuilder.buildDatabasePersistenceError(exception);
        } else if (exception instanceof HttpMessageConversionException) {
            generalError = ErrorBuilder.buildJsonMappingException(exception);
        } else {
            generalError = ErrorBuilder.buildUncaughtException(exception);
        }

        ModelAndView model = new ModelAndView();

        model.addObject(generalError);

        model.setViewName("errors/error");
        return model;

    }

}
