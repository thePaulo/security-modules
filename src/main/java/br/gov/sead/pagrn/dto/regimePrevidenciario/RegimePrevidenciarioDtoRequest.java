package br.gov.sead.pagrn.dto.regimePrevidenciario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegimePrevidenciarioDtoRequest {

    private String denominacao;
    
    private String mneumonico;
}