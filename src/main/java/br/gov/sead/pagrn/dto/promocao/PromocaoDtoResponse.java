package br.gov.sead.pagrn.dto.promocao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PromocaoDtoResponse {

    private Long id;

    private String processoAdministrativo;

    private LocalDate dataPromocao;

    //evento

    private LocalDate dataVigencia;

    private String descricao;

}

