package br.gov.sead.auth.annotations;

import br.gov.sead.auth.service.DecodeToken;
import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.dto.GenericEventoDtoRequest;
import br.gov.sead.pagrn.dto.provimentoDeCargo.ProvimentoDeCargoDtoRequest;
import br.gov.sead.pagrn.service.VinculoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
public class ValidarVinculoAspect {
    @Autowired
    private VinculoService vinculoService;

    @Around("@annotation(ValidarVinculo)")
    public Object ValidarVinculo(ProceedingJoinPoint joinPoint) throws Throwable {
        // recupera o objeto httpServletRequest no metodo do endpoint
        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[1];

        // obtem a string do token JWT
        String tokenStr = getSecurityContext().getTokenString();

        // decodifica o token
        DecodeToken decodedToken = DecodeToken.getDecoded(tokenStr);

        //System.out.println(decodedToken.preferred_username);
        //System.out.println(Arrays.toString(Arrays.stream(decodedToken.groups).toArray()));

        // recupera o dto request
        GenericEventoDtoRequest dto = (GenericEventoDtoRequest) joinPoint.getArgs()[0];

        Long vinculoResponsavelId = dto.getVinculoResponsavelId();
        //Long vinculoid = dto.getVinculoId();
        String username = decodedToken.preferred_username;

        if (!validarVinculo(username, vinculoResponsavelId)) {
            return ResponseEntity.status(403).body("Not allowed operation");
        }

        return joinPoint.proceed();
    }

    private KeycloakSecurityContext getSecurityContext() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }

    /*
    public String getUsername() {
        return getSecurityContext().getToken().getPreferredUsername();
    }
    */

    private Boolean validarVinculo(String cpf, Long vinculo){
        Optional<Vinculo> v = vinculoService.findById(vinculo);

        // vinculo nao existe
        if (v.isEmpty()) {
            return false;
        }

        // verifica se o vinculo e ativo
        if (v.get().getSituacao() != SituacaoVinculo.ATIVO) {
            return false;
        }

        // vefifica se o vinculo pertence ao cpf
        List<Long> vinculosId = vinculoService.findByCPFdoServidor(cpf);
        if (!vinculosId.contains(v.get().getId())) {
            return false;
        }

        System.out.println("PASSSOU NA VALIDAÇAO DE VINCULO");
        return true;
    }
}