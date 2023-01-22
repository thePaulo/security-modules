package br.gov.sead.pagrn.dto.carreiraCargo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarreiraCargoDtoResponse {

    private Long id;

    private Long carreiraId;

    private Long cargoId;
    
    private String grau;
}
