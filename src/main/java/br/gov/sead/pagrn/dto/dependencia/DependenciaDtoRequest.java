package br.gov.sead.pagrn.dto.dependencia;

import br.gov.sead.pagrn.domain.type.TipoDependencia;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DependenciaDtoRequest {

    private Long dependente;

    private TipoDependencia tipoDependencia;

    private LocalDate dataVigencia;

    private LocalDate dataExtincao;

}
