package com.imcode.entities.enums;

import com.imcode.entities.Address;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vitaly on 13.05.15.
 */
@Entity
@Table(name = "dbo_address_type")
public class _AddressType {
    public static enum AddressTypeEnum {
        REGISTERED(0, "Registered address"),
        RESIDENTIAL(1, "Residential address"),
        BOARDER(2, "Boarder address");

        private String representation;
        private int id;

        AddressTypeEnum(int id, String representation) {
            this.representation = representation;
        }

        // the identifierMethod
        public int toInt() {
            return id;
        }

        // the valueOfMethod
        public static AddressTypeEnum fromInt(int id) {
            for (AddressTypeEnum addressTypeEnum : values()) {
                if (addressTypeEnum.id == id)
                    return addressTypeEnum;
            }

            throw new IllegalArgumentException();
        }

        @Override
        public String toString() {
            return representation;
        }
    }

    public _AddressType() { }

    public _AddressType(AddressTypeEnum addressType) {
        this.id = addressType.id;
        this.addressType = addressType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "name", columnDefinition = "integer", nullable = true)
//    @Type(
//            type = "org.appfuse.tutorial.commons.hibernate.GenericEnumUserType",
//            parameters = {
//                    @Parameter(
//                            name  = "enumClass",
//                            value = "com.imcode.entities.enums.AddressType$AddressTypeEnum"),
//                    @Parameter(
//                            name  = "identifierMethod",
//                            value = "toInt"),
//                    @Parameter(
//                            name  = "valueOfMethod",
//                            value = "fromInt")
//            }
//    )
    @Enumerated(EnumType.STRING)
    private AddressTypeEnum addressType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressType")
    private Set<Address> addresses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AddressTypeEnum getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressTypeEnum addressType) {
        this.addressType = addressType;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}

