package com.imcode.controllers;

import com.imcode.exceptions.MessagingException;
import org.springframework.ui.Model;
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

    Object create(T entity, WebRequest webRequest) throws MessagingException;

    // Updating entity
    Object update(ID id, T entity, WebRequest webRequest);

    //Deleting entity
    Object delete(ID id, WebRequest webRequest);

}
