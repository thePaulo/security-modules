package br.gov.sead.pagrn.dto.regimePrevidenciario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegimePrevidenciarioDtoResponse {
    
    private Long id;

    private String denominacao;

    private String mneumonico;
}