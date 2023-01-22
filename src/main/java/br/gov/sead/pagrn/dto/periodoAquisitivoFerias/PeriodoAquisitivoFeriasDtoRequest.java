package br.gov.sead.pagrn.dto.periodoAquisitivoFerias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PeriodoAquisitivoFeriasDtoRequest {

    private LocalDate dataInicio;

    private LocalDate dataFim;

}
