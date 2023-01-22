package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "remuneracoes_basicas")
@SQLDelete(sql = "UPDATE remuneracoes_basicas SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class RemuneracaoBasica extends AbstractEntity {

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private BigDecimal remuneracaoBasica;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private LocalDate dataVigencia;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "jornada_trabalho_id")
    private JornadaTrabalho jornadaTrabalho;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "nivel_desempenho_id")
    private NivelDesempenho nivelDesempenho;
}