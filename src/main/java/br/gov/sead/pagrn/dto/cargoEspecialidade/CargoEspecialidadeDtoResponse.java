package br.gov.sead.pagrn.dto.cargoEspecialidade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CargoEspecialidadeDtoResponse {
    
    private Long id;

    private Long cargoId;

    private Long especialidadeId;
}