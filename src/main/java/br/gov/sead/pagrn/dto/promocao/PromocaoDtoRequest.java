package br.gov.sead.pagrn.dto.promocao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PromocaoDtoRequest {

    private Long vinculo;

    //Promocao

    private Long nivelCargo;

    private LocalDate dataPromocao;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
