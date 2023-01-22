package br.gov.sead.pagrn.dto.servidor;

import br.gov.sead.pagrn.domain.type.Escolaridade;
import br.gov.sead.pagrn.dto.pessoaFisica.PessoaFisicaDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServidorDtoResponse {

    private Long id;

    private PessoaFisicaDtoResponse pessoaFisica;

    private String matricula;

    private Escolaridade escolaridade;

    private String pispasep;

    private String nomeSocial;

}
