package com.imcode.controllers;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;

/**
 * Created by root on 27.03.15.
 */
public interface CrudController<T, ID extends Serializable> {

    // Getting entity by id
    Object get(ID id, WebRequest webRequest);

    //Getting list of entities
    Object getAll(WebRequest webRequest, Model model);

    Object create(T entity, BindingResult bindingResult, WebRequest webRequest) throws MethodArgumentNotValidException;

    // Updating entity
    Object update(ID id, T entity, WebRequest webRequest);

    //Deleting entity
    Object delete(ID id, WebRequest webRequest);

}
