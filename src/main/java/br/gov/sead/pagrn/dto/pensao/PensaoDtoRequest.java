package br.gov.sead.pagrn.dto.pensao;

import br.gov.sead.pagrn.domain.type.TipoPensao;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PensaoDtoRequest {

    private Long pensionista;

    private TipoPensao tipoPensao;

    private Boolean isentoIR;

    private Double percentual;

    private LocalDate dataVigencia;

    private LocalDate dataExtincao;
}
