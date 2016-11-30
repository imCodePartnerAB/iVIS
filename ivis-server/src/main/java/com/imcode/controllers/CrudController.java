package com.imcode.controllers;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 27.03.15.
 */
public interface CrudController<T, ID extends Serializable> {

    // Getting entity by id
    T get(ID id, HttpServletResponse response, WebRequest webRequest) throws Exception;

    //Getting list of entities
    List<T> getAll(WebRequest webRequest, HttpServletResponse response, Model model) throws Exception;

    T create(T entity,
                  HttpServletResponse response,
                  BindingResult bindingResult,
                  WebRequest webRequest) throws Exception;

    // Updating entity
    T update(ID id,
                  HttpServletResponse response,
                  T entity,
                  BindingResult bindingResult,
                  WebRequest webRequest) throws Exception;

    //Deleting entity
    T delete(ID id,
                  HttpServletResponse response,
                  WebRequest webRequest) throws Exception;

}
