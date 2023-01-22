package br.gov.sead.pagrn.dto.encerramento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EncerramentoDtoResponse {

    private Long id;

    //evento
    private LocalDate dataRegistro;

    private LocalDate dataVigencia;

    private String descricao;
}
