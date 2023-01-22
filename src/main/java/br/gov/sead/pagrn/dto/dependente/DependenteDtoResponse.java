package br.gov.sead.pagrn.dto.dependente;

import br.gov.sead.pagrn.domain.type.TipoDependencia;
import br.gov.sead.pagrn.domain.type.TipoParentesco;
import br.gov.sead.pagrn.dto.pessoaFisica.PessoaFisicaDtoResponse;
import br.gov.sead.pagrn.dto.servidor.ServidorDtoResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class DependenteDtoResponse {

    private Long id;

    private PessoaFisicaDtoResponse pessoaFisica;

    private ServidorDtoResponse servidor;

    private TipoParentesco tipoParentesco;

    private LocalDate dataVigencia;

    private LocalDate dataExtincao;

}
