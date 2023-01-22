package br.gov.sead.pagrn.dto.reversao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReversaoDtoResponse {
    //Reversao

    private Long id;

    private String dadosLaudo;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
