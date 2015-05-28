package com.imcode.entities;

import com.imcode.entities.enums.AddressTypeEnum;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vitaly on 13.05.15.
 */
@Entity
@Table(name = "dbo_address")
public class Address extends AbstractIdEntity implements Serializable {
    @Column
    private Integer postalCode;

    @Column
    private Long municipality_code;

    @Column(length = 50)
    private String city;

    @Column
    private String street;

    @Column
    private String propertyDescription;

    @Column
    private String careOf;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressTypeEnum addressType;

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Long getMunicipality_code() {
        return municipality_code;
    }

    public void setMunicipality_code(Long municipality_code) {
        this.municipality_code = municipality_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public AddressTypeEnum getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressTypeEnum addressType) {
        this.addressType = addressType;
    }
}
