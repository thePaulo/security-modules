package br.gov.sead.pagrn.dto.falecimento;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class FalecimentoDtoRequest {

    private String processoAdministrativo;

    private Long servidor;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
