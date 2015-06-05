package com.imcode.entities.oauth2;

import com.imcode.entities.User;
import com.imcode.entities.enums.AuthorizedGrantType;
import com.imcode.oauth2.IvisClientDetails;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.client.Jackson2ArrayOrStringDeserializer;
import org.springframework.security.oauth2.provider.client.JacksonArrayOrStringDeserializer;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by vitaly on 09.02.15.
 */

@SuppressWarnings("serial")
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_DEFAULT)
@org.codehaus.jackson.annotate.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="dbo_oauth_client_details")
public class JpaClientDetails implements IvisClientDetails, Serializable {

    @Id
    @Column(name = "id", length = 36, nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.codehaus.jackson.annotate.JsonProperty("client_id")
    @com.fasterxml.jackson.annotation.JsonProperty("client_id")
    private String clientId;

    @Column(length = 100, unique = true)
    @org.codehaus.jackson.annotate.JsonProperty("client_name")
    @com.fasterxml.jackson.annotation.JsonProperty("client_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerId")
    @org.codehaus.jackson.annotate.JsonProperty("client_owner")
    @com.fasterxml.jackson.annotation.JsonProperty("client_owner")
    private User owner;

    @Column
    @org.codehaus.jackson.annotate.JsonProperty("client_secret")
    @com.fasterxml.jackson.annotation.JsonProperty("client_secret")
    private String clientSecret;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dbo_oauth_client_scope", joinColumns = @JoinColumn(name = "clientId"))
    @org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
    @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
    private Set<String> scope = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dbo_oauth_client_resources",joinColumns = @JoinColumn(name = "clientId"))
    @Column(name = "resourceId")
    @org.codehaus.jackson.annotate.JsonProperty("resource_ids")
    @org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
    @com.fasterxml.jackson.annotation.JsonProperty("resource_ids")
    @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
    private Set<String> resourceIds = Collections.emptySet();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dbo_oauth_client_garant_types",joinColumns = @JoinColumn(name = "clientId"))
    @Column(name = "authorizedGrantType")
    @org.codehaus.jackson.annotate.JsonProperty("authorized_grant_types")
    @org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
    @com.fasterxml.jackson.annotation.JsonProperty("authorized_grant_types")
    @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
    private Set<String> authorizedGrantTypes = Collections.emptySet();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dbo_oauth_client_redirect_uris",joinColumns = @JoinColumn(name = "clientId"))
    @org.codehaus.jackson.annotate.JsonProperty("redirect_uri")
    @org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
    @com.fasterxml.jackson.annotation.JsonProperty("redirect_uri")
    @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
    private Set<String> registeredRedirectUris;

    @Transient
    @org.codehaus.jackson.annotate.JsonProperty("autoapprove")
    @org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
    @com.fasterxml.jackson.annotation.JsonProperty("autoapprove")
    @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
    private Set<String> autoApproveScopes;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, targetEntity = ClientRole.class)
    @JoinTable(name = "dbo_oauth_client_roles_cross",
            joinColumns = @JoinColumn(name = "clientId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<ClientRole> authorities;

    @Column
    @org.codehaus.jackson.annotate.JsonProperty("access_token_validity")
    @com.fasterxml.jackson.annotation.JsonProperty("access_token_validity")
    private Integer accessTokenValiditySeconds;

    @Column
    @org.codehaus.jackson.annotate.JsonProperty("refresh_token_validity")
    @com.fasterxml.jackson.annotation.JsonProperty("refresh_token_validity")
    private Integer refreshTokenValiditySeconds;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @MapKeyColumn(name = "name", length = 100)
    @Column(name = "value")
    @CollectionTable(name = "dbo_oauth_client_additional_info",joinColumns = @JoinColumn(name = "clientId"))
    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();

    public JpaClientDetails() {
    }

    public JpaClientDetails(JpaClientDetails prototype) {
        setAccessTokenValiditySeconds(prototype.getAccessTokenValiditySeconds());
        setRefreshTokenValiditySeconds(prototype.getRefreshTokenValiditySeconds());
//        setAuthorities(prototype.getAuthorities());
        setAuthorizedGrantTypes(prototype.getAuthorizedGrantTypes());
//        setClientId(prototype.getClientId());
        setClientSecret(prototype.getClientSecret());
        setRegisteredRedirectUri(prototype.getRegisteredRedirectUri());
        setScope(prototype.getScope());
        setResourceIds(prototype.getResourceIds());
        setOwner(prototype.getOwner());
        setName(prototype.getName());
    }

    public JpaClientDetails(String name, User owner, String resourceIds,
                             String scopes, String grantTypes, String authorities) {
        this(name, owner, resourceIds, scopes, grantTypes, authorities, null);
    }

    public JpaClientDetails(String name, User owner, String resourceIds,
                             String scopes, String grantTypes, String authorities,
                             String redirectUris) {

        this.name = name;
        this.owner = owner;

        if (StringUtils.hasText(resourceIds)) {
            Set<String> resources = StringUtils
                    .commaDelimitedListToSet(resourceIds);
            if (!resources.isEmpty()) {
                this.resourceIds = resources;
            }
        }

        if (StringUtils.hasText(scopes)) {
            Set<String> scopeList = StringUtils.commaDelimitedListToSet(scopes);
            if (!scopeList.isEmpty()) {
                this.scope = scopeList;
            }
        }

        if (StringUtils.hasText(grantTypes)) {
            this.authorizedGrantTypes = StringUtils
                    .commaDelimitedListToSet(grantTypes);
        } else {
            this.authorizedGrantTypes = new HashSet<String>(Arrays.asList(
                    "authorization_code", "refresh_token"));
        }

//        if (StringUtils.hasText(authorities)) {
//            this.authorities = new HashSet<>(AuthorityUtils
//                    .commaSeparatedStringToAuthorityList(authorities));
//        }

        if (StringUtils.hasText(redirectUris)) {
            this.registeredRedirectUris = StringUtils
                    .commaDelimitedListToSet(redirectUris);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setAutoApproveScopes(Collection<String> autoApproveScopes) {
        this.autoApproveScopes = new HashSet<String>(autoApproveScopes);
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Set<String> getAutoApproveScopes() {
        return autoApproveScopes;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (autoApproveScopes == null) {
            return false;
        }
        for (String auto : autoApproveScopes) {
            if (auto.equals("true") || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope == null ? Collections.<String> emptySet()
                : new LinkedHashSet<String>(scope);
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds == null ? Collections
                .<String> emptySet() : new LinkedHashSet<String>(resourceIds);
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = new LinkedHashSet<String>(
                authorizedGrantTypes);
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUri(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris == null ? null
                : new LinkedHashSet<String>(registeredRedirectUris);
    }

    @org.codehaus.jackson.annotate.JsonProperty("authorities")
    @com.fasterxml.jackson.annotation.JsonProperty("authorities")
    private List<String> getAuthoritiesAsStrings() {
        return new ArrayList<String>(
                AuthorityUtils.authorityListToSet(authorities));
    }

    @org.codehaus.jackson.annotate.JsonProperty("authorities")
    @org.codehaus.jackson.map.annotate.JsonDeserialize(using = JacksonArrayOrStringDeserializer.class)
    @com.fasterxml.jackson.annotation.JsonProperty("authorities")
    @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = Jackson2ArrayOrStringDeserializer.class)
    private void setAuthoritiesAsStrings(Set<String> values) {
//        setAuthorities(new HashSet<>(AuthorityUtils.createAuthorityList(values
//                .toArray(new String[values.size()]))));
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (ClientRole authority :this.authorities) {
            authorities.add(authority);
        }

        return  authorities;
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setAuthorities(Set<ClientRole> authorities) {
        this.authorities = new HashSet<>(authorities);
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Set<ClientRole> getRoles() {
        return authorities;
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setRoles(Set<ClientRole> authorities) {
        this.authorities = new HashSet<>(authorities);
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    @org.codehaus.jackson.annotate.JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(
            Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public void setAdditionalInformation(Map<String, ?> additionalInformation) {
        this.additionalInformation = new LinkedHashMap<String, Object>(
                additionalInformation);
    }

    @org.codehaus.jackson.annotate.JsonAnyGetter
    @com.fasterxml.jackson.annotation.JsonAnyGetter
    public Map<String, Object> getAdditionalInformation() {
        return Collections.unmodifiableMap(this.additionalInformation);
    }

    @org.codehaus.jackson.annotate.JsonAnySetter
    @com.fasterxml.jackson.annotation.JsonAnySetter
    public void addAdditionalInformation(String key, Object value) {
        this.additionalInformation.put(key, value);
    }


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((accessTokenValiditySeconds == null) ? 0
                : accessTokenValiditySeconds);
        result = prime
                * result
                + ((refreshTokenValiditySeconds == null) ? 0
                : refreshTokenValiditySeconds);
        result = prime * result
                + ((authorities == null) ? 0 : authorities.hashCode());
        result = prime
                * result
                + ((authorizedGrantTypes == null) ? 0 : authorizedGrantTypes
                .hashCode());
        result = prime * result
                + ((clientId == null) ? 0 : clientId.hashCode());
        result = prime * result
                + ((clientSecret == null) ? 0 : clientSecret.hashCode());
        result = prime
                * result
                + ((registeredRedirectUris == null) ? 0
                : registeredRedirectUris.hashCode());
        result = prime * result
                + ((resourceIds == null) ? 0 : resourceIds.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        result = prime * result + ((additionalInformation == null) ? 0 : additionalInformation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JpaClientDetails other = (JpaClientDetails) obj;
        if (accessTokenValiditySeconds != other.accessTokenValiditySeconds)
            return false;
        if (refreshTokenValiditySeconds != other.refreshTokenValiditySeconds)
            return false;
        if (authorities == null) {
            if (other.authorities != null)
                return false;
        } else if (!authorities.equals(other.authorities))
            return false;
        if (authorizedGrantTypes == null) {
            if (other.authorizedGrantTypes != null)
                return false;
        } else if (!authorizedGrantTypes.equals(other.authorizedGrantTypes))
            return false;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        if (clientSecret == null) {
            if (other.clientSecret != null)
                return false;
        } else if (!clientSecret.equals(other.clientSecret))
            return false;
        if (registeredRedirectUris == null) {
            if (other.registeredRedirectUris != null)
                return false;
        } else if (!registeredRedirectUris.equals(other.registeredRedirectUris))
            return false;
        if (resourceIds == null) {
            if (other.resourceIds != null)
                return false;
        } else if (!resourceIds.equals(other.resourceIds))
            return false;
        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        if (additionalInformation == null) {
            if (other.additionalInformation != null)
                return false;
        } else if (!additionalInformation.equals(other.additionalInformation))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JpaClientDetails [clientId=" + clientId + ", clientSecret="
                + clientSecret + ", scope=" + scope + ", resourceIds="
                + resourceIds + ", authorizedGrantTypes="
                + authorizedGrantTypes + ", registeredRedirectUris="
                + registeredRedirectUris + ", authorities=" + authorities
                + ", accessTokenValiditySeconds=" + accessTokenValiditySeconds
                + ", refreshTokenValiditySeconds="
                + refreshTokenValiditySeconds + ", additionalInformation="
                + additionalInformation + "]";
    }

//    private User owner;
//
//    private String name;
//
//    public JpaClientDetails() {
//        super();
//    }
//
//    public JpaClientDetails(ClientDetails prototype) {
//        super(prototype);
//    }
//
//    public JpaClientDetails(String name, String resourceIds, String scopes, String grantTypes, String authorities) {
//        super(null, resourceIds, scopes, grantTypes, authorities);
//        this.name = name;
//    }
//
//    public JpaClientDetails(String name, String resourceIds, String scopes, String grantTypes, String authorities, String redirectUris) {
//        super(null, resourceIds, scopes, grantTypes, authorities, redirectUris);
//        this.name = name;
//    }
//
//    @Id
//    @Column(name = "id", length = 36, nullable = false)
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @Override
//    public String getClientId() {
//        return super.getClientId();
//    }
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "dbo_oauth_client_resources",joinColumns = @JoinColumn(name = "clientId"))
//    @Column(name = "resourceId")
//    @Override
//    public Set<String> getResourceIds() {
//        return super.getResourceIds();
//    }
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "dbo_oauth_client_scope", joinColumns = @JoinColumn(name = "clientId"))
//    @Override
//    public Set<String> getScope() {
//        return super.getScope();
//    }
//
//    @Column
//    @Override
//    public String getClientSecret() {
//        return super.getClientSecret();
//    }
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "dbo_oauth_client_garant_types",joinColumns = @JoinColumn(name = "clientId"))
//    @Column(name = "authorizedGrantType")
//    @Override
//    public Set<String> getAuthorizedGrantTypes() {
//        return super.getAuthorizedGrantTypes();
//    }
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "dbo_oauth_client_redirect_uris",joinColumns = @JoinColumn(name = "clientId"))
//    @Override
//    public Set<String> getRegisteredRedirectUri() {
//        return super.getRegisteredRedirectUri();
//    }
//
//    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, targetEntity = ClientRole.class)
//    @JoinTable(name = "dbo_oauth_client_roles_cross",
//            joinColumns = @JoinColumn(name = "clientId"),
//            inverseJoinColumns = @JoinColumn(name = "roleId"))
//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        return super.getAuthorities();
//    }
//
//    @Column
//    @Override
//    public Integer getAccessTokenValiditySeconds() {
//        return super.getAccessTokenValiditySeconds();
//    }
//
//    @Column
//    @Override
//    public Integer getRefreshTokenValiditySeconds() {
//        return super.getRefreshTokenValiditySeconds();
//    }
//
//    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
//    @MapKeyColumn(name = "name", length = 100)
//    @Column(name = "value")
//    @CollectionTable(name = "dbo_oauth_client_additional_info",joinColumns = @JoinColumn(name = "clientId"))
//    @Override
//    public Map<String, Object> getAdditionalInformation() {
//        return super.getAdditionalInformation();
//    }
//
//    @Column
//    public String getName() {
//        return name;
//    }
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ownerId")
//    public User getOwner() {
//        return owner;
//    }
//
//    public void setOwner(User owner) {
//        this.owner = owner;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

//    Setter utilities
    public void setScope(String... scope) {
        setScope(new HashSet<String>(Arrays.asList(scope)));
    }

    public void setScope(String scope) {
        setScope(new HashSet<String>(Arrays.asList(scope)));
    }

    public void setResourceIds(String... resourceIds) {
        setResourceIds(new HashSet<String>(Arrays.asList(resourceIds)));
    }

    public void setResourceIds(String resourceId) {
        setResourceIds(new HashSet<String>(Arrays.asList(resourceId)));
    }

    public void setRegisteredRedirectUri(String... redirectUris) {
        setRegisteredRedirectUri(new HashSet<String>(Arrays.asList(redirectUris)));
    }

    public void setRegisteredRedirectUri(String redirectUri) {
        setRegisteredRedirectUri(new HashSet<String>(Arrays.asList(redirectUri)));
    }

    public void setAuthoritiesOverload(GrantedAuthority... authorities) {
//        setAuthorities(new HashSet<>(Arrays.asList(authorities)));
    }

    public void setAuthoritiesOverload(List<? extends GrantedAuthority> authorities) {
//        setAuthorities(new HashSet<>(authorities));
    }

    public void setAuthoritiesOverload(GrantedAuthority authority) {
//        setAuthorities(new HashSet<>(Arrays.asList(authority)));
    }

    public void setAuthorizedGrantTypes(AuthorizedGrantType... authorizedGrantTypes) {
        Set<String> grantTypesStrings = new HashSet<>();

        for (AuthorizedGrantType authorizedGrantType : authorizedGrantTypes) {
            grantTypesStrings.add(authorizedGrantType.toString());
        }

        setAuthorizedGrantTypes(grantTypesStrings);
    }

    public void setAuthorizedGrantTypes(AuthorizedGrantType authorizedGrantType) {
        setAuthorizedGrantTypes(new HashSet<String>(Arrays.asList(authorizedGrantType.toString())));
    }

    @Override
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


//    public void setScope(Set<String> scope) {
//        this.scope = scope;
//    }
//
//    public void setResourceIds(Set<String> resourceIds) {
//        this.resourceIds = resourceIds;
//    }
//
//    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
//        this.authorizedGrantTypes = authorizedGrantTypes;
//    }
//
//    public void setRegisteredRedirectUris(Set<String> registeredRedirectUris) {
//        this.registeredRedirectUris = registeredRedirectUris;
//    }
//
//    public void setAutoApproveScopes(Set<String> autoApproveScopes) {
//        this.autoApproveScopes = autoApproveScopes;
//    }
//
//    public void setAuthorities(List<GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }

}
