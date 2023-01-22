package br.gov.sead.pagrn.dto.unidadeOrganizacional;

import br.gov.sead.pagrn.dto.pessoaJuridica.PessoaJuridicaDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UnidadeOrganizacionalDtoResponse {

    private Long id;

    private String sigla;

    private String codigoLegado;

    private LocalDate dataInicioOperacao;

    private LocalDate dataExtincao;

    private String codIbgeCnae;

    private PessoaJuridicaDtoResponse pessoaJuridica;

}
