package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.BaseAlimentos;
import br.gov.sead.pagrn.domain.type.TipoReajuste;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "pensoes_alimenticias")
@SQLDelete(sql = "UPDATE pensionistas  SET removed = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PensaoAlimenticia extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "dependencia_id")
    private Dependencia dependencia;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private BaseAlimentos baseAlimentos;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private TipoReajuste tipoReajuste;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Double percentual;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Boolean incide13;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Boolean incideFeriasAdiantamento;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Boolean incideFerias;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Boolean incideAbonoPecuario;

    private String observacoes;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataInicio;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataFinal;


    public void preencher(Dependencia dependencia) {
        this.setDependencia(dependencia);
    }
}
