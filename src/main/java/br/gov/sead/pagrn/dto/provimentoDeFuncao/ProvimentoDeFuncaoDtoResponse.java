package br.gov.sead.pagrn.dto.provimentoDeFuncao;

import br.gov.sead.pagrn.domain.type.RegimeJuridico;
import br.gov.sead.pagrn.domain.type.TipoVinculo;
import br.gov.sead.pagrn.dto.funcao.FuncaoDtoResponse;
import br.gov.sead.pagrn.dto.pessoaJuridica.PessoaJuridicaDtoResponse;
import br.gov.sead.pagrn.dto.unidadeOrganizacional.UnidadeOrganizacionalDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ProvimentoDeFuncaoDtoResponse {

    private Long id;

    //provimento de funcao
    private PessoaJuridicaDtoResponse pessoaJuridicaContratante;

    private UnidadeOrganizacionalDtoResponse uoPagante;

    private RegimeJuridico regimeJuridico;

    private TipoVinculo tipoVinculo;

    private FuncaoDtoResponse funcaoDtoResponse;

    private LocalDate dataNomeacao;

    private LocalDate dataPosse;

    private LocalDate dataExercicio;

    private String processoAdministrativo;

    private boolean opcaoVencimento;

    //evento

    private LocalDate dataRegistro;

    private LocalDate dataVigencia;

    private String descricao;


}
