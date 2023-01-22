package br.gov.sead.pagrn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericEventoDtoRequest {
    private Long vinculoId;
    private Long VinculoResponsavelId;
}
