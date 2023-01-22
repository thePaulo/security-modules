package br.gov.sead.motorDeCalculo.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class IPE extends TabelaDeDesconto {

    private static final long serialVersionUID = 1L;

    public IPE(BigDecimal limiteInferior, BigDecimal limiteSuperior, String aliquota) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.aliquota = aliquota;
    }
}

