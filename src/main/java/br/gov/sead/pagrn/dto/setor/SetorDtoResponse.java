package br.gov.sead.pagrn.dto.setor;

import br.gov.sead.pagrn.dto.endereco.EnderecoDtoResponse;
import br.gov.sead.pagrn.dto.unidadeOrganizacional.UnidadeOrganizacionalDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SetorDtoResponse {

    private Long id;

    private String sigla;

    private String denominacao;

    private SetorDtoResponse setorSuperior;

    private EnderecoDtoResponse endereco;

    private UnidadeOrganizacionalDtoResponse unidadeOrganizacional;

}
