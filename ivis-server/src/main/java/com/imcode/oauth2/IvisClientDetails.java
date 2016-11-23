package com.imcode.oauth2;

import com.imcode.entities.User;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * Created by vitaly on 15.05.15.
 */
public interface IvisClientDetails extends ClientDetails{
    String getName();

    User getOwner();
}
