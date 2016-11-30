package com.imcode.controllers.html;

import com.imcode.entities.User;
import com.imcode.entities.interfaces.JpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.repository.support.DefaultRepositoryInvokerFactory;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.repository.support.RepositoryInvoker;
import org.springframework.data.repository.support.RepositoryInvokerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
@RequestMapping("/domain/{entityClassName}")
public class DomainController {
    @Autowired
    private ApplicationContext applicationContext;

    private Repositories repositories;

    private RepositoryInvokerFactory repositoryInvokerFactory;

    private final int DEFAULT_PAGE_NUMBER = 1;

    private final int DEFAULT_PAGE_SIZE = 50;

    private final Sort DEFAULT_PAGE_SORT = new Sort(Sort.Direction.ASC, "id");

    private final Pageable DEFAULT_LIST_PAGE = new PageRequest(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, DEFAULT_PAGE_SORT);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ConversionService conversionService;

//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    private UserValidator userValidator;

    @Autowired
    MessageSource messageSource;

    @PostConstruct
    public void init() {
        repositories = new Repositories(applicationContext);
        repositoryInvokerFactory = new DefaultRepositoryInvokerFactory(repositories, conversionService);
    }

    //    Shows the list of users
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@PathVariable("entityClassName") String entityClassName,
                             ModelAndView model,
                             Authentication authentication) {

        EntityType entityType = getEntityType(entityClassName);

        if (entityType == null) {
            throw new EntityNotFoundException();
        }


        Class entityClass = entityType.getJavaType();
        RepositoryInvoker repositoryInvoker = repositoryInvokerFactory.getInvokerFor(entityClass);

        if (repositoryInvoker == null) {
            throw new RepositoryNotFoundException();
        }


        Iterable<Object> entities = repositoryInvoker.invokeFindAll(DEFAULT_PAGE_SORT);

        model.setViewName("domain/list/" + entityClassName.getClass().getSimpleName());
        model.getModelMap().clear();
        model.addObject("entities", entities);

        return model;
    }

    //    Show the UPDATE form
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") User user,
                                   ModelAndView model,
                                   WebRequest webRequest,
                                   Locale locale) {

//        if (user == null) {
//            model.setViewName("clients/list");
//            throw new NotFoundException();
////            model.addObject(new Message(MessageType.ERROR, messageSource.getMessage("entity.notFoundById", new Object[]{User.class.getSimpleName(), id}, locale)));
//        }
//
//        model.setViewName("users/edit");
//        model.addObject(user);
//        model.addObject(roleService.findAll());

        return model;
    }

    //    Show the CREATE form
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model) {
//        model.setViewName("users/edit");
//        model.addObject(roleService.findAll());
//        User user = new User();
//        Role roleUser = roleService.findByName("ROLE_USER");
//        model.addObject(user);
//
//        if (roleUser != null) {
//            user.setAuthorities(roleUser);
//        }

        return model;
    }

    //    CREATE new user
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResultUser,
                               ModelAndView model,
                               WebRequest webRequest,
                               Locale locale) {

//        if (webRequest.isUserInRole("ROLE_ADMIN")) {
//            user.setConfirmPassword(user.getPassword());
//        }
//
//        ValidationUtils.invokeValidator(userValidator, user, bindingResultUser);
//
//        if (userService.findByUsername(user.getUsername()) != null) {
//            bindingResultUser.rejectValue("username", null, "User is alredy exists!");
//        }
//
//        if (bindingResultUser.hasErrors()) {
//            model.addObject(user);
//            model.setViewName("users/edit");
//
//            return model;
//        }
//
//        userService.save(user);

        model.setViewName("redirect:/users");

        return model;
    }


//    @ModelAttribute("user")
//    public User bindUser(User user) {
//        Set<Role> roleSet = new HashSet<>();
//        Set<Role> roleNameSet = user.getAuthorities();
//
//        for (Role role :roleNameSet) {
//            Role persistRole = roleService.findByName(role.getName());
//            if (persistRole != null) {
//                roleSet.add(persistRole);
//            }
//        }
//
//        user.setAuthorities(roleSet);
//
//        return user;
//    }

    //    UPDATE exists user
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") User persistUser,
                               @ModelAttribute("user") @Valid User user,
                               BindingResult bindingResultUser,
                               ModelAndView model,
                               WebRequest webRequest,
                               Locale locale) {

//            if (webRequest.isUserInRole("ROLE_ADMIN")) {
//                user.setConfirmPassword(user.getPassword());
//            }
//
//            ValidationUtils.invokeValidator(userValidator, user, bindingResultUser);
//
//            if (bindingResultUser.hasErrors()) {
//                model.addObject(user);
//                model.setViewName("users/edit");
//
//                return model;
//            }
//
////            User persistUser = userService.find(id);
//
//            if (persistUser == null) {
//                throw new NotFoundException();
//            }
//
//            String[] fieldExceptions = user.getPassword().isEmpty() ? new String[]{"id", "password", "confirmPassword"} : new String[]{"id"};
//
//            BeanUtils.copyProperties(user, persistUser, fieldExceptions);
//            userService.save(persistUser);
//            model.setViewName("redirect:/users");

        return model;
    }

    //    DELETE exists user
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
//        if (!userService.exist(id)) {
//            throw new NotFoundException();
//        }
//
//        userService.delete(id);

//        return "redirect:/users";
    }

    private EntityType getEntityType(String className) {
        Set<EntityType<?>> entitySet = entityManager.getMetamodel().getEntities();
        for (EntityType entityType :entitySet) {
            String entityClassName = entityType.getJavaType().getSimpleName();
            if (className.equals(entityClassName)) {
                return entityType;
            }
        }

        return null;
    }

    }

class DomainControllerException extends RuntimeException {

}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class EntityNotFoundException extends DomainControllerException {}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class RepositoryNotFoundException extends DomainControllerException {}

