package br.gov.sead.pagrn.dto.demissao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class DemissaoDtoRequest {

    //Demissao
    private String motivo;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;

    // FK
    private Long vinculo;

}
