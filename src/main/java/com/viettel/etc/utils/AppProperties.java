package com.viettel.etc.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@Configuration
public class AppProperties {

    @Value("${kcl.login}")
    public String keycloakLogin;

    @Value("${kcl.url.user.member}")
    public String ccUserMember;

    @Value("${keycloak.credentials.secret}")
    public String keycloakCcSecret;

    @Value("${kcl.cc.admin.username}")
    public String keycloakCcAdminUserName;

    @Value("${kcl.cc.admin.userpass}")
    public String keycloakCcAdminUserPass;

    @Value("${keycloak.resource}")
    public String keycloakCcClientId;

    @Value("${kcl.cc.group.id}")
    public String keycloakCcGroupId;

}
