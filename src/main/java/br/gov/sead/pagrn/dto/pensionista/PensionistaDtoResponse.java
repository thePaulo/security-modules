package br.gov.sead.pagrn.dto.pensionista;

import br.gov.sead.pagrn.domain.type.TipoParentesco;
import br.gov.sead.pagrn.dto.pessoaFisica.PessoaFisicaDtoResponse;
import br.gov.sead.pagrn.dto.vinculo.VinculoDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PensionistaDtoResponse {

    private VinculoDtoResponse vinculo;

    private PessoaFisicaDtoResponse pessoaPensionista;

    private PessoaFisicaDtoResponse pessoaProcurador;

    private TipoParentesco tipoParentesco;

    private String observacoes;
}
