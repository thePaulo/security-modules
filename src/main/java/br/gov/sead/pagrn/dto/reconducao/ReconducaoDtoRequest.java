package br.gov.sead.pagrn.dto.reconducao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReconducaoDtoRequest {

    private String motivo;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;

    // FK
    private Long vinculo;
}
