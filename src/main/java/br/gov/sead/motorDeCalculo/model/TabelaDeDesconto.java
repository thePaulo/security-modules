package br.gov.sead.motorDeCalculo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TabelaDeDesconto extends AbstractEntity {

    protected BigDecimal limiteInferior;

    protected BigDecimal limiteSuperior;

    protected String aliquota;

}

