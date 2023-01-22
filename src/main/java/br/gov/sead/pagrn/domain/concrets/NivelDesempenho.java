package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "niveis_desempenho")
@SQLDelete(sql = "UPDATE niveis_desempenho SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class NivelDesempenho extends AbstractEntity{
    
    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String sigla;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String grau;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "grupo_ocupacional_id")
    private GrupoOcupacional grupoOcupacional;
}