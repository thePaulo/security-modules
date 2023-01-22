package br.gov.sead.pagrn.dto.reintegracao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReintegracaoDtoRequest {

    //Reintegração

    private LocalDate dataReintegracao;

    private String motivo;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;

    // FK

    private Long vinculo;
}
