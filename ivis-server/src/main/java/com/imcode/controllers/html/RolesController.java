package com.imcode.controllers.html;

import com.imcode.entities.Permission;
import com.imcode.entities.Role;
import com.imcode.services.PermissionService;
import com.imcode.services.RoleService;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 16.11.16.
 */
@Controller
@RequestMapping("/" + RolesController.MAIN_PATH)
public class RolesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String MAIN_PATH = "roles";

    private final RoleService mainService;
    private final PermissionService permissionService;

    @Autowired
    public RolesController(RoleService roleService, PermissionService permissionService) {
        this.mainService = roleService;
        this.permissionService = permissionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName(MAIN_PATH + "/list");
        model.addObject("entityList", mainService.findAllNonInternal());
        return model;
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Role entity,
                                   ModelAndView model) throws MethodArgumentNotValidException {

        StaticUtls.rejectNullValue(entity, "try invoke update form for non exist role");

        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);
        model.addObject("permissionsAll", permissionService.findAll());
        return model;
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model) {
        Role entity = new Role();
        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);
        model.addObject("permissionsAll", permissionService.findAll());
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("entity") @Valid Role entity,
                               ModelAndView model) throws MethodArgumentNotValidException {

        BindingResult bindingResult = new BeanPropertyBindingResult(entity, "role");
        new GeneralValidator(true, "id").invoke(entity, bindingResult);
        entity.setInternal(false);
        mainService.save(entity);
        model.setViewName("redirect:/" + MAIN_PATH);
        return model;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") Role persistEntity,
                               @ModelAttribute("entity") @Valid Role entity,
                               ModelAndView model) throws MethodArgumentNotValidException, InvocationTargetException, IllegalAccessException {

        StaticUtls.rejectNullValue(persistEntity, "Try update non exist role");
        entity.setInternal(false);
        StaticUtls.nullAwareBeanCopy(persistEntity, entity);
        persistEntity.setPermissions(entity.getPermissions());
        mainService.save(persistEntity);
        model.setViewName("redirect:/" + MAIN_PATH);
        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) throws MethodArgumentNotValidException {

        Role role = mainService.find(id);

        StaticUtls.rejectNullValue(role, "Try delete non exist role");

        mainService.delete(id);

    }


    @RequestMapping(value = "/available_urls", method = RequestMethod.GET)
    @ResponseBody
    public Set<String> getAllAvailableUrls() {
        return permissionService
                .findAll()
                .stream()
                .map(permission -> permission.getUrl().replace("/api/v1/{format}/", ""))
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
