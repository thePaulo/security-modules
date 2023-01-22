package br.gov.sead.pagrn.dto.reconducao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReconducaoDtoResponse {
    //Reversao

    private Long id;

    private String motivo;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
