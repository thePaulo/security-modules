package br.gov.sead.pagrn.dto.encerramento;

import br.gov.sead.pagrn.dto.GenericEventoDtoRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EncerramentoDtoRequest extends GenericEventoDtoRequest {

    //evento
    private LocalDate dataVigencia;

    private String descricao;
}
