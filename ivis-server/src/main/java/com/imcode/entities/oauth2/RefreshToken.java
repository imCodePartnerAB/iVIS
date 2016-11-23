package com.imcode.entities.oauth2;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.persistence.*;

/**
 * Created by vitaly on 25.05.15.
 */
@Entity
@Table(name = "oauth_refresh_token")
public class RefreshToken {
    @Id
    @Column(name = "token_id", length = 36)
    private String tokenId;

    @Lob
    @Column(name = "token")
    private RefreshToken token;

    @Lob
    @Column(name = "authentication")
    private OAuth2Authentication authentication;
}
