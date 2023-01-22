package br.gov.sead.pagrn.dto.exoneracao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ExoneracaoDtoRequest {

    private String processoAdministrativo;

    private Long vinculo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
