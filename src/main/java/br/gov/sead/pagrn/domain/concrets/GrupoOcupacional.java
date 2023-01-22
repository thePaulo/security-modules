package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.Escolaridade;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "grupos_ocupacionais")
@SQLDelete(sql = "UPDATE grupos_ocupacionais SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GrupoOcupacional extends AbstractEntity {

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String denominacao;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private int anosADTS;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "PCCR_id")
    private PCCR pccr;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "rubrica_id")
    private Rubrica rubrica;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;
}
