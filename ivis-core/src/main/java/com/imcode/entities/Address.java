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
    private String municipalityCode;

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

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipality_code) {
        this.municipalityCode = municipality_code;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder()
                .append(addressType)
                .append(postalCode)
                .append(city)
                .append('(')
                .append(municipalityCode)
                .append("), ")
                .append(street);

        return sb.toString();
    }
}
