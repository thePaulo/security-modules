package br.gov.sead.pagrn.dto.afastamento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AfastamentoDtoRequest {

    private Long vinculo;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private LocalDate dataVigencia;

    private String descricao;

}
