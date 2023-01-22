package br.gov.sead.pagrn.dto.designacao;

import br.gov.sead.pagrn.dto.GenericEventoDtoRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class DesignacaoDtoRequest extends GenericEventoDtoRequest {

    private Long funcao;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
