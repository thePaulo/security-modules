package br.gov.sead.pagrn.dto.deficiencia;

import br.gov.sead.pagrn.domain.type.TipoDeficiencia;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeficienciaDtoRequest {

    private TipoDeficiencia tipoDeficiencia;

    private String observacao;

}
