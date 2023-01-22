package br.gov.sead.pagrn.dto.vinculo;

import br.gov.sead.pagrn.domain.type.RegimeJuridico;
import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
import br.gov.sead.pagrn.domain.type.TipoVinculo;
import br.gov.sead.pagrn.dto.funcao.FuncaoDtoResponse;
import br.gov.sead.pagrn.dto.pessoaFisica.PessoaFisicaDtoResponse;
import br.gov.sead.pagrn.dto.servidor.ServidorDtoResponse;
import br.gov.sead.pagrn.dto.setor.SetorDtoResponse;
import br.gov.sead.pagrn.dto.unidadeOrganizacional.UnidadeOrganizacionalDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VinculoDtoResponse {

    private Long id;

    private SetorDtoResponse setor;

    private PessoaFisicaDtoResponse pessoaFisicaContratante;

    private UnidadeOrganizacionalDtoResponse uoPagante;

    private RegimeJuridico regimeJuridico;

    private TipoVinculo tipoVinculo;

    private SituacaoVinculo situacao;

    private ServidorDtoResponse servidor;

}
