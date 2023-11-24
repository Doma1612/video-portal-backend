/* (C)2023 */
package de.video.rest;

import de.video.security.PlainSHA512PasswordHash;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@BasicAuthenticationMechanismDefinition()
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "video_portal_JNDI",
        callerQuery = "select PASSWORD from SP_t_user where USERNAME=?",
        groupsQuery = "select ROLENAME as GROUPNAME from SP_t_user_roles where USERNAME=?",
        hashAlgorithm = PlainSHA512PasswordHash.class)
@ApplicationScoped
@Named
@ApplicationPath("api")
public class ApplicationConfig extends Application {}
