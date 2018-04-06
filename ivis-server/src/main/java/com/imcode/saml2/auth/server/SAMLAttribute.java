package com.imcode.saml2.auth.server;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SAMLAttribute {

    private final String name;
    private final String value;
}
