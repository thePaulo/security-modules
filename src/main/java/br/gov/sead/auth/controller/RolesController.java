package br.gov.sead.auth.controller;

import br.gov.sead.auth.model.LoginRequest;
import br.gov.sead.auth.service.LoginService;
import br.gov.sead.auth.utils.AuthUtils;
import org.apache.http.protocol.HTTP;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class RolesController {

    @GetMapping("/roles")
    public ResponseEntity<Set<String>> getRoles() {

        return new ResponseEntity<>(AuthUtils.getCurrentUserRoles(),
                AuthUtils.getSecurityContext() == null ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
    }
}
