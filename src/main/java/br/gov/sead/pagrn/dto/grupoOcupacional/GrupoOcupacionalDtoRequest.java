package br.gov.sead.pagrn.dto.grupoOcupacional;

import br.gov.sead.pagrn.domain.type.Escolaridade;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GrupoOcupacionalDtoRequest {

    private String denominacao;

    private Integer anosADTS;

    private Long pccrId;

    private Long rubricaId;

    private Escolaridade escolaridade;
}
