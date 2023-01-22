package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.TipoPensao;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
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

@Entity(name = "pensoes")
@SQLDelete(sql = "UPDATE pensionistas  SET removed = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Pensao extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "pensionista_id")
    private Pensionista pensionista;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private TipoPensao tipoPensao;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Boolean isentoIR;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Double percentual;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataVigencia;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataExtincao;

    public void preencher(Pensionista pensionista) {
        this.setPensionista(pensionista);
    }


}
