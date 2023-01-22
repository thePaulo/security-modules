package br.gov.sead.auth.annotations;

import br.gov.sead.auth.service.DecodeToken;
import br.gov.sead.pagrn.domain.concrets.Tag;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
@Aspect
@Component
public class ValidarEscopoAspect {
    @Autowired
    private VinculoService vinculoService;

    @Around("@annotation(ValidarEscopo)")
    public Object ValidarEscopo(ProceedingJoinPoint joinPoint) throws Throwable {
        // recupera o objeto httpServletRequest no metodo do endpoint
        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[1];

        // obtem a string do token JWT
        String tokenStr = getSecurityContext().getTokenString();

        // recupera o dto request
        ProvimentoDeCargoDtoRequest dto = (ProvimentoDeCargoDtoRequest) joinPoint.getArgs()[0];

        Long vinculoResponsavelId = dto.getVinculoResponsavelId();
        Long vinculoid = dto.getVinculoId();

        if (!validarEscopo(vinculoid, vinculoResponsavelId)) {
            return ResponseEntity.status(403).body("The scope do not allow this operation.");
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(EscopoGlobal)")
    public Object EscopoGlobal(ProceedingJoinPoint joinPoint) throws Throwable {
        // recupera o objeto httpServletRequest no metodo do endpoint
        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[1];

        // obtem a string do token JWT
        String tokenStr = getSecurityContext().getTokenString();

        // recupera o dto request
        ProvimentoDeCargoDtoRequest dto = (ProvimentoDeCargoDtoRequest) joinPoint.getArgs()[0];

        Long vinculoResponsavelId = dto.getVinculoResponsavelId();

        if (!escopoGlobal(vinculoResponsavelId)) {
            return ResponseEntity.status(403).body("The scope do not allow this operation.");
        }

        return joinPoint.proceed();
    }

    private KeycloakSecurityContext getSecurityContext() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }

    private Boolean validarEscopo(Long vinculoId, Long vinculoResponsavelId) {
        if (escopoGlobal(vinculoResponsavelId)) { return true; }

        Optional<Vinculo> vinculoOp = vinculoService.findById(vinculoId);
        Optional<Vinculo> vinculoResponsavelOp = vinculoService.findById(vinculoResponsavelId);

        if (vinculoOp.isPresent() && vinculoResponsavelOp.isPresent()) {
            Vinculo vinculo = vinculoOp.get();
            Vinculo vinculoResponsavel = vinculoResponsavelOp.get();

            return vinculo.getSetor().getUnidadeOrganizacional() == vinculoResponsavel.getSetor().getUnidadeOrganizacional();
        }
        return false;
    }


    private Boolean escopoGlobal(Long vinculoId) {
        Optional<Vinculo> vinculoOp = vinculoService.findById(vinculoId);

        if (vinculoOp.isPresent()) {
            Vinculo vinculo = vinculoOp.get();
            Set<Tag> tags = vinculo.getSetor().getTags();
            for (Tag tag : tags) {
                if (Objects.equals(tag.getMneumonico(), "RH_GLOBAL")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
