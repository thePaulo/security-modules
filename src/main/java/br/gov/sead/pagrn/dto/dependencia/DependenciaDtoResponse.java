package br.gov.sead.pagrn.dto.dependencia;

import br.gov.sead.pagrn.domain.type.TipoDependencia;
import br.gov.sead.pagrn.dto.dependente.DependenteDtoResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DependenciaDtoResponse {

    private DependenteDtoResponse dependente;

    private TipoDependencia tipoDependencia;

    private LocalDate dataVigencia;

    private LocalDate dataExtincao;
}
