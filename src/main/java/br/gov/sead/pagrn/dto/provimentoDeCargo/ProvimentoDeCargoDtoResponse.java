package br.gov.sead.pagrn.dto.provimentoDeCargo;

import br.gov.sead.pagrn.domain.concrets.Especialidade;
import br.gov.sead.pagrn.domain.concrets.RemuneracaoBasica;
import br.gov.sead.pagrn.domain.type.RegimeJuridico;
import br.gov.sead.pagrn.domain.type.TipoVinculo;
import br.gov.sead.pagrn.dto.pessoaFisica.PessoaFisicaDtoResponse;
import br.gov.sead.pagrn.dto.pessoaJuridica.PessoaJuridicaDtoResponse;
import br.gov.sead.pagrn.dto.unidadeOrganizacional.UnidadeOrganizacionalDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ProvimentoDeCargoDtoResponse {

    private Long id;

    //provimento de cargo
    private PessoaJuridicaDtoResponse pessoaJuridicaContratante;

    private UnidadeOrganizacionalDtoResponse uoPagante;

    private RegimeJuridico regimeJuridico;

    private TipoVinculo tipoVinculo;

    private RemuneracaoBasica remuneracaoBasica;

    private Especialidade especialidade;

    private LocalDate dataNomeacao;

    private LocalDate dataPosse;

    private LocalDate dataExercicio;

    private String processoAdministrativo;

    //evento

    private LocalDate dataRegistro;

    private LocalDate dataVigencia;

    private String descricao;


}
