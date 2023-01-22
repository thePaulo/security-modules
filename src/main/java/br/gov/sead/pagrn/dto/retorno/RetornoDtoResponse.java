package br.gov.sead.pagrn.dto.retorno;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RetornoDtoResponse {
    //Retorno

    private Long id;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
