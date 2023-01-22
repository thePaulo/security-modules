package br.gov.sead.pagrn.dto.unidadeOrganizacional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UnidadeOrganizacionalDtoRequest {

    private String sigla;

    private String codigoLegado;

    private LocalDate dataInicioOperacao;

    private LocalDate dataExtincao;

    private String codIbgeCnae;

    private Long pessoaJuridica;

}
