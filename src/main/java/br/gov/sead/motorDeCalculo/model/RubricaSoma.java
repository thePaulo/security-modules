package br.gov.sead.motorDeCalculo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RubricaSoma {
    
    private String codRubrica;

    private BigDecimal somatorio;

}