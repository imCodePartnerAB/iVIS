package com.imcode.entities.superclasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.interfaces.JpaContactedPerson;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by vitaly on 13.05.15.
 */
//@Entity
//@Table(name = "dbo_person")
@MappedSuperclass
public abstract class AbstractPerson extends AbstractIdEntity<Long> implements Serializable, JpaPersonalizedEntity, JpaContactedPerson {
    @Column
    @JsonProperty("personal_id")
    private String personalId;

    @Column
    @JsonProperty("first_name")
    private String firstName;

    @Column
    @JsonProperty("last_name")
    private String lastName;

    public AbstractPerson() {
    }

    public AbstractPerson(String pid, String firstName, String lastName) {
        this.personalId = pid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (StringUtils.hasText(firstName))
            addWord(sb, firstName);

        if (StringUtils.hasText(lastName))
            addWord(sb, lastName);


        if (sb.length() == 0)
            addWord(sb, personalId);
        ;

        return sb.toString();
    }

    private void addWord(StringBuilder sb, String word) {
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
            sb.append(' ');
        }

        sb.append(word);
    }

    protected  static <K extends Enum<K>, V extends ContactInformation<K>> void putAddressValueIntoMap(Class<K> enumClass, V addressValue, Map<K, V> map) {
        Objects.requireNonNull(addressValue);
        K addressValueType = addressValue.getType();
        Objects.requireNonNull(addressValueType);

        if (map == null) {
            map = new EnumMap<>(enumClass);
        }

        map.put(addressValueType, addressValue);
    }

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (!super.deepEquals(entity)) {
            return false;
        }

        AbstractPerson that = (AbstractPerson) entity;

        return Objects.equals(this.personalId, that.personalId)
               && Objects.equals(this.firstName, that.firstName)
               && Objects.equals(this.lastName, that.lastName)
//               && Objects.equals(this., that.)
                ;
    }
}
