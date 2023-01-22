package br.gov.sead.pagrn.dto.carreiraCargo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarreiraCargoDtoRequest {

    private Long carreiraId;

    private Long cargoId;
    
    private String grau;
}
