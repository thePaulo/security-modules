package br.gov.sead.motorDeCalculo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class IRRF extends TabelaDeDesconto {

    private static final long serialVersionUID = 1L;

    private BigDecimal parcelaDedutivel;

    public IRRF(BigDecimal limiteInferior, BigDecimal limiteSuperior, String aliquota, BigDecimal parcelaDedutivel) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.aliquota = aliquota;
        this.parcelaDedutivel = parcelaDedutivel;
    }
}

