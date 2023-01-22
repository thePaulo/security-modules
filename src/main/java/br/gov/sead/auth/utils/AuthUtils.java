package br.gov.sead.auth.utils;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class AuthUtils {

    public static KeycloakSecurityContext getSecurityContext() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }

    public static Set<String> getCurrentUserRoles(){
        KeycloakSecurityContext context = getSecurityContext();
        if(context==null) {
            Set<String> error = new HashSet<String>();
            error.add("Usuário não autenticado");
            return error;
        }
        return context.getToken().getRealmAccess().getRoles();
    }
}
