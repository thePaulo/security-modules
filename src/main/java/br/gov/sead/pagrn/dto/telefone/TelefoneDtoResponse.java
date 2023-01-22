package br.gov.sead.pagrn.dto.telefone;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TelefoneDtoResponse {

    private Long id;

    private String celular;

    private String residencial;

}
