package br.gov.sead.pagrn.dto.pccr;

import java.time.LocalDate;

import br.gov.sead.pagrn.domain.type.RegimeJuridico;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PccrDtoResponse {

    private Long id;

    private String denominacao;

    private LocalDate dataVigencia;

    private LocalDate dataExtincao;

    private RegimeJuridico regimeJuridico;
}
