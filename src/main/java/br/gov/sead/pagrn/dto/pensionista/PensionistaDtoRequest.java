package br.gov.sead.pagrn.dto.pensionista;

import br.gov.sead.pagrn.domain.type.TipoParentesco;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PensionistaDtoRequest {

    //FK
    private Long vinculo;

    private Long pessoaPensionista;

    private Long pessoaProcurador;

    private TipoParentesco tipoParentesco;

    private String observacoes;
}
