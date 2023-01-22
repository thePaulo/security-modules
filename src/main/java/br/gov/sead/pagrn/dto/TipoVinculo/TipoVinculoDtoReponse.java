package br.gov.sead.pagrn.dto.TipoVinculo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TipoVinculoDtoReponse {

    private Long id;

    private String denominacao;

    private String mneumonico;
}
