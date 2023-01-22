package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "periodos_aquisitivos_ferias")
@SQLDelete(sql = "UPDATE periodos_aquisitivos_ferias SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PeriodoAquisitivoFerias extends AbstractEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "vinculos_id")
    private Vinculo vinculo;

    private LocalDate dataInicio;

    private LocalDate dataFim;
}
