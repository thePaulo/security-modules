package br.gov.sead.pagrn.dto.setor;

import br.gov.sead.pagrn.dto.endereco.EnderecoDtoRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class SetorDtoRequest {

    private String sigla;

    private String denominacao;

    private EnderecoDtoRequest endereco;

    private Long setorSuperior;

    private Long unidadeOrganizacional;

}
