package com.imcode.services.csv;

import com.imcode.controllers.html.form.upload.FileOption;
import com.imcode.entities.Guardian;
import com.imcode.entities.Person;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.entities.superclasses.AbstractAddressValue;
import com.imcode.services.GenericService;
import com.imcode.services.GuardianService;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by vitaly on 08.12.15.
 */
@Component
@Scope("request")
public class GuardianFieldSetMapper extends CsvFieldSetMapper<Guardian> {
    private static class AddressValueSetter implements BiConsumer<JpaPersonalizedEntity, String> {

        private final Function<JpaPersonalizedEntity, ? extends AbstractAddressValue<?>> addressGetter;

        AddressValueSetter(Function<JpaPersonalizedEntity, ? extends AbstractAddressValue<?>> addressGetter) {

            this.addressGetter = addressGetter;
        }

        public void accept(JpaPersonalizedEntity personalizedEntity, String value) {
            AbstractAddressValue<?> addressValue = addressGetter.apply(personalizedEntity);
            addressValue.setValue(value);
        }


    }

    @Autowired
    private GuardianService service;

    private Supplier<Guardian> entitySupplier = () -> {
        Guardian guardian = new Guardian();
        guardian.setPerson(new Person());
        return guardian;
    };

    private Map<String, BiConsumer<? super Guardian, String>> setters = new HashMap<>();

    private Map<String, Function<String, ? super Guardian>> finders = new HashMap<>();

    private FileOption fileOption;

    public GuardianFieldSetMapper() {
        init();
    }

    public FileOption getFileOption() {
        return fileOption;
    }

    public void setFileOption(FileOption fileOption) {
        this.fileOption = fileOption;
    }

    @Override
    public void setService(GenericService<Guardian, ?> service) {
        this.service = (GuardianService) service;
    }

    @Override
    public Guardian mapFieldSet(FieldSet fieldSet) throws BindException {
        String finderName = fileOption.getColumnNameList().get(fileOption.getKeyColumn());
        Function<String, Guardian> finder = (Function<String, Guardian>) finders.get(finderName);
        String keyValue = fieldSet.readString(finderName);
        Guardian guardian = finder.apply(keyValue);
        for (String columnName : fieldSet.getNames()) {
            BiConsumer<? super Guardian, String> setter = (BiConsumer<? super Guardian, String>) setters.get(columnName);
            if (setter != null) {
                setter.accept(guardian, fieldSet.readString(columnName));
            }
        }


        return guardian;
    }

    //    @PostConstruct
    private void init() {

        //Finders try to find entity in JPA, if instance not found creates a new one.
        Function<String, Guardian> idFinder = id -> Optional.ofNullable(service.find(Long.valueOf(id))).orElseGet(entitySupplier);
        Function<String, Guardian> personalIdFinder = personalId -> Optional.ofNullable(service.findFirstByPersonalId(personalId)).orElseGet(entitySupplier);

        finders.put("id", idFinder);
        finders.put("personalId", personalIdFinder);

        //Setters, set the fields
        abstract class AbstractAddressValueSetter<P extends JpaPersonalizedEntity, T extends Enum<T>, E extends AbstractAddressValue<T>> implements BiFunction<P, T, E> {
            abstract public E getAddressValue(P person, T enumType);

            @Override
            public E apply(P p, T t) {
                Person person = p.getPerson();
                E addressValue = getAddressValue(p, t);
                return addressValue;
            }
        }

        BiConsumer<? super Guardian, String> emailSetter = new AddressValueSetter(jpaPersonalizedEntity -> {
            Person person = jpaPersonalizedEntity.getPerson();
            Email addressValue = person.getEmail(CommunicationTypeEnum.HOME);
            if (addressValue == null) {
                addressValue = Email.of(CommunicationTypeEnum.HOME);
                person.setEmail(addressValue);
            }
            return addressValue;
        });

        BiConsumer<? super Guardian, String> phoneSetter = new AddressValueSetter(jpaPersonalizedEntity -> {
            Person person = jpaPersonalizedEntity.getPerson();
            Phone addressValue = person.getPhone(CommunicationTypeEnum.HOME);
            if (addressValue == null) {
                addressValue = Phone.of(CommunicationTypeEnum.HOME);
                person.setPhone(addressValue);
            }
            return addressValue;
        });

        BiConsumer<Guardian, String> personalId = (g, pid) -> g.getPerson().setPersonalId(pid);
        BiConsumer<Guardian, String> firstName = (g, fn) -> g.getPerson().setFirstName(fn);
        BiConsumer<Guardian, String> lastName = (g, ln) -> g.getPerson().setFirstName(ln);
        BiConsumer<? super Guardian, String> email = emailSetter;
        BiConsumer<? super Guardian, String> phone = phoneSetter;
        //todo make setter for addresses
//        BiConsumer<Guardian, String>=(g,) -> g.getPerson().set();

        setters.put("personalId", personalId);
        setters.put("firstName", firstName);
        setters.put("lastName", lastName);
        setters.put("email", email);
        setters.put("phone", phone);
        //todo make setter for addresses
//        setters.put("address", );
    }

    public GuardianService getService() {
        return service;
    }

    public void setService(GuardianService service) {
        this.service = service;
    }

    public Supplier<Guardian> getEntitySupplier() {
        return entitySupplier;
    }

    public void setEntitySupplier(Supplier<Guardian> entitySupplier) {
        this.entitySupplier = entitySupplier;
    }

    public Map<String, BiConsumer<? super Guardian, String>> getSetters() {
        return setters;
    }

    public void setSetters(Map<String, BiConsumer<? super Guardian, String>> setters) {
        this.setters = setters;
    }

    public Map<String, Function<String, ? super Guardian>> getFinders() {
        return finders;
    }

    public void setFinders(Map<String, Function<String, ? super Guardian>> finders) {
        this.finders = finders;
    }

    public Set<String> getFieldNames() {
        return setters.keySet();
    }

    public Set<String> getIndexFieldNames() {
        return finders.keySet();
    }


}

