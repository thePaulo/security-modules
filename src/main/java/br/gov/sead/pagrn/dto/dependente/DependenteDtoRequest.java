package br.gov.sead.pagrn.dto.dependente;

import br.gov.sead.pagrn.domain.type.TipoDependencia;
import br.gov.sead.pagrn.domain.type.TipoParentesco;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DependenteDtoRequest {

    private Long pessoaFisica;

    private Long servidor;

    private TipoParentesco tipoParentesco;

    private LocalDate dataVigencia;

    private LocalDate dataExtincao;
}
