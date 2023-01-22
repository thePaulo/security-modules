package br.gov.sead.pagrn.dto.aproveitamento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AproveitamentoDtoRequest {

    //aproveitamento
    private LocalDate dataExercicio;

    private LocalDate dataPosse;

    private LocalDate dataAproveitamento;

    private String processoAdministrativo;

    //evento
    private LocalDate dataVigencia;

    private String descricao;

    // FK
    private Long vinculo;

    private Long nivelCargo;

    private Long setor;
}
