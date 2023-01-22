package br.gov.sead.pagrn.dto.servidor;

import br.gov.sead.pagrn.domain.type.Escolaridade;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServidorDtoRequest {

    private Long pessoaFisica;

    private String matricula;

    private Escolaridade escolaridade;

    private String pispasep;

    private String nomeSocial;

}



