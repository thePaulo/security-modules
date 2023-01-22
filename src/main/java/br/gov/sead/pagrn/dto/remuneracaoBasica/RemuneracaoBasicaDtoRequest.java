package br.gov.sead.pagrn.dto.remuneracaoBasica;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RemuneracaoBasicaDtoRequest {
    
    private BigDecimal remuneracaoBasica;

    private LocalDate dataVigencia;

    private Long cargoId;

    private Long jornadaId;

    private Long nivelDesempenhoId;
}
