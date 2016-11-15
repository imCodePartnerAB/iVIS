package com.imcode.controllers;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by root on 27.03.15.
 */
public interface CrudController<T, ID extends Serializable> {

    // Getting entity by id
    Object get(ID id, HttpServletResponse response, WebRequest webRequest) throws Exception;

    //Getting list of entities
    Object getAll(WebRequest webRequest, HttpServletResponse response, Model model) throws Exception;

    Object create(T entity,
                  HttpServletResponse response,
                  BindingResult bindingResult,
                  WebRequest webRequest) throws Exception;

    // Updating entity
    Object update(ID id,
                  HttpServletResponse response,
                  T entity,
                  BindingResult bindingResult,
                  WebRequest webRequest) throws Exception;

    //Deleting entity
    Object delete(ID id,
                  HttpServletResponse response,
                  WebRequest webRequest) throws Exception;

}
