package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.TipoDependencia;
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

@Entity(name = "dependencias")
@SQLDelete(sql = "UPDATE pensionistas  SET removed = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Dependencia extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "dependente_id")
    Dependente dependente;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    TipoDependencia tipoDependencia;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataVigencia;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataExtincao;


    public void preencher(Dependente dependente) {
        this.setDependente(dependente);
    }
}
