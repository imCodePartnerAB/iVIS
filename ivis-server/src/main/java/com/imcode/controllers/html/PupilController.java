package com.imcode.controllers.html;

import com.imcode.controllers.html.exceptions.NotFoundException;
import com.imcode.entities.Guardian;
import com.imcode.entities.Person;
import com.imcode.entities.Pupil;
import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.services.*;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/" + PupilController.MAIN_PATH)
public class PupilController {
    public static final String MAIN_PATH = "pupils";
    @Autowired
    private PupilService mainService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private PersonService personService;

    @Autowired
    private SchoolClassService schoolClassService;

    @Autowired
    private AcademicYearService academicYearService;

//    @Autowired
//    private UserValidator userValidator;

    @Autowired
    MessageSource messageSource;

    //    Shows the list of users
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model, Authentication authentication) {
        model.setViewName(MAIN_PATH + "/list");
        model.addObject("entityList", mainService.findAll());

        return model;
    }

    //    Show the UPDATE form
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") Pupil entity,
                                   ModelAndView model,
                                   WebRequest webRequest,
                                   Locale locale) {
        addSpecialObjects(model, entity);

        if (entity == null) {
            model.setViewName(MAIN_PATH + "/list");
            throw new NotFoundException();
        }

        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);

        return model;
    }

    //    Show the CREATE form
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public ModelAndView createForm(ModelAndView model) {
        Pupil entity = new Pupil();
        addSpecialObjects(model, entity);
        model.setViewName(MAIN_PATH + "/edit");
        model.addObject("entity", entity);

        return model;
    }

    //    CREATE new user
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("entity") @Valid Pupil entity,
                               BindingResult bindingResultEntity,
                               ModelAndView model,
                               WebRequest webRequest,
                               Locale locale) {

        addSpecialObjects(model, entity);
//        ValidationUtils.invokeValidator(userValidator, entity, bindingResultPupil);
//
//        if (mainService.findByUsername(entity.getUsername()) != null) {
//            bindingResultUser.rejectValue("username", null, "User is alredy exists!");
//        }

        if (bindingResultEntity.hasErrors()) {
            model.addObject("entity", entity);
            model.setViewName(MAIN_PATH + "/edit");

            return model;
        }

        mainService.save(entity);

        model.setViewName("redirect:/" + MAIN_PATH);

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
    public ModelAndView update(@PathVariable("id") @ModelAttribute("entity") Pupil persistEntity,
                               @ModelAttribute("entity") @Valid Pupil entity,
                               BindingResult bindingResultEntity,
                               ModelAndView model,
                               WebRequest webRequest,
                               Locale locale) {
        addSpecialObjects(model, entity);

//            ValidationUtils.invokeValidator(userValidator, entity, bindingResultUser);

        if (bindingResultEntity.hasErrors()) {
            model.addObject("entity", entity);
            model.setViewName(MAIN_PATH + "/edit");

            return model;
        }

//            User persistUser = mainService.find(id);

        if (persistEntity == null) {
            throw new NotFoundException();
        }

//            String[] fieldExceptions = {"id", "person.firstName"};

//            BeanUtils.copyProperties(entity, persistEntity, fieldExceptions);
//            mainService.save(persistEntity);

        Person persistPerson = persistEntity.getPerson();
        Person entityPerson = entity.getPerson();

        persistPerson.setPersonalId(entityPerson.getPersonalId());
        persistPerson.setFirstName(entityPerson.getFirstName());
        persistPerson.setLastName(entityPerson.getLastName());

        personService.save(persistPerson);
        model.setViewName("redirect:/" + MAIN_PATH);

        return model;
    }

    //    DELETE exists user
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        if (!mainService.exist(id)) {
            throw new NotFoundException();
        }

        mainService.delete(id);

//        return "redirect:/schools";
    }

    protected void addSpecialObjects(ModelAndView model, Pupil entity) {
        model.addObject("schoolList", schoolService.findAll());
        model.addObject("schoolClassList", schoolClassService.findAll());
        model.addObject("academicYearList", academicYearService.findAll());
    }




//    public static void main(String[] args) throws Exception {
////        final String fileName = "/home/vitaly/SkypeFiles/Guardians.csv";
////        List<Guardian> list = null;
////
////        FieldSetMapper<Guardian> fieldMapper = new GuardianFieldMapper();
////        list = mapGuardianCostom(fileName, fieldMapper);
////        System.out.println(list);
////
////        BeanWrapperFieldSetMapper<Guardian> beanMapper = new BeanWrapperFieldSetMapper<>();
////        beanMapper.setTargetType(Guardian.class);
////        list = mapGuardianCostom(fileName, beanMapper);
////        System.out.println(list);
////        String[] tokens = {"foo", "1", "true"};
////
////        FieldSet fs = new DefaultFieldSet(tokens);
////        System.out.println(fs.readString(0));
////        System.out.println(fs.readInt(1));
////        System.out.println(fs.readBoolean(2));
//    }

//    private static <T> List<T> mapGuardianCostom(String fileName, FieldSetMapper<T> fieldMapper) throws Exception {
//        List<T> result = new ArrayList<>();
//        FileSystemResource resource = new FileSystemResource(fileName);
//        FlatFileItemReader<T> itemReader = new FlatFileItemReader<>();
//        itemReader.setResource(resource);
//
//        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();
//        final DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames("lastName,firstName,personalId,careOf,street,postalCode,city,phone,email,guardianTo".split(","));
//        lineMapper.setLineTokenizer(tokenizer);
//        lineMapper.setFieldSetMapper(fieldMapper);
//
//        itemReader.setLineMapper(lineMapper);
//        itemReader.setLinesToSkip(1);
//        itemReader.open(new ExecutionContext());
//
//        T g = null;
//        while ((g = itemReader.read()) != null) {
//            result.add(g);
//        }
//
//        return result;
//    }
}

//class GuardianFieldMapper implements FieldSetMapper<Guardian> {
////    private final Class<Guardian> entityClass;
////
////    GuardianFieldMapper(Class<Guardian> entityClass) {
////        this.entityClass = entityClass;
////    }
//
//    @Override
//    public Guardian mapFieldSet(FieldSet fieldSet) throws BindException {
//        Person person = new Person();
//        Guardian guardian = new Guardian();
//
//        guardian.setPerson(person);
//        person.setFirstName(fieldSet.readRawString("firstName"));
//        person.setLastName(fieldSet.readRawString("lastName"));
//        person.setPersonalId(fieldSet.readRawString("personalId"));
//
//        Address address = Address.of(AddressTypeEnum.REGISTERED);
//        address.setCareOf(fieldSet.readRawString("careOf"));
//        address.setStreet(fieldSet.readRawString("street"));
//        address.setPostalCode(fieldSet.readInt("postalCode"));
//        address.setCity(fieldSet.readRawString("city"));
//        person.setAddress(address);
//
//        Email email = Email.of(CommunicationTypeEnum.HOME, fieldSet.readRawString("email"));
//        person.setEmail(email);
//
//        Phone phone = Phone.of(CommunicationTypeEnum.HOME, fieldSet.readRawString("phone"));
//        person.setPhone(phone);
//
//        return guardian;
//    }
//}
