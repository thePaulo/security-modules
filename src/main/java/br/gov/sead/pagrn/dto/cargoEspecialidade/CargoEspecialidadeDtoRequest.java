package br.gov.sead.pagrn.dto.cargoEspecialidade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CargoEspecialidadeDtoRequest {

    private Long cargoId;

    private Long especialidadeId;
    
}