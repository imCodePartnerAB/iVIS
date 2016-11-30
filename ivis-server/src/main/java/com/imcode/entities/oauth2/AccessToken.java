package com.imcode.entities.oauth2;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.persistence.*;

/**
 * Created by vitaly on 25.05.15.
 */
@Entity
@Table(name = "oauth_access_token")
public class AccessToken {
    @Id
    @Column(name = "token_id", length = 36)
    private String tokenId;

    @Lob
    @Column(name = "token")
    private AccessToken token;

    @Column(name = "authentication_id", length = 36)
    private String authenticationId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "client_id", length = 36)
    private String clientId;

    @Lob
    @Column(name = "authentication")
    private OAuth2Authentication authentication;

    @Column(name = "refresh_token", length = 36)
    private String refreshToken;

}
