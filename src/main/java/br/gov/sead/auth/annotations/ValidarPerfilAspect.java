package br.gov.sead.auth.annotations;

import br.gov.sead.auth.service.DecodeToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class ValidarPerfilAspect {

    @Around("@annotation(GestorRH)")
    public Object GestorRH(ProceedingJoinPoint joinPoint) throws Throwable {
        // recupera o objeto httpServletRequest no metodo do endpoint
        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[1];

        // obtem a string do token JWT
        String tokenStr = getSecurityContext().getTokenString();

        // decodifica o token
        DecodeToken decodedToken = DecodeToken.getDecoded(tokenStr);

        // recupera lista de grupos do usuario
        //String[] groups = decodedToken.groups;
        List<String> groups = new ArrayList<String>(Arrays.asList(decodedToken.groups));

        //System.out.println(Arrays.toString(Arrays.stream(decodedToken.groups).toArray()));

        if (!groups.contains("/GESTAO_RH")) {
            return ResponseEntity.status(403).body("The designated profile cannot perform this action");
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(AnalistaRH)")
    public Object AnalistaRH(ProceedingJoinPoint joinPoint) throws Throwable {
        // recupera o objeto httpServletRequest no metodo do endpoint
        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[1];

        // obtem a string do token JWT
        String tokenStr = getSecurityContext().getTokenString();

        // decodifica o token
        DecodeToken decodedToken = DecodeToken.getDecoded(tokenStr);

        // recupera lista de grupos do usuario
        //String[] groups = decodedToken.groups;
        List<String> groups = new ArrayList<String>(Arrays.asList(decodedToken.groups));

        //System.out.println(Arrays.toString(Arrays.stream(decodedToken.groups).toArray()));

        if (!groups.contains("/ANALISTA_RH")) {
            return ResponseEntity.status(403).body("The designated profile cannot perform this action");
        }

        return joinPoint.proceed();
    }

    private KeycloakSecurityContext getSecurityContext() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
