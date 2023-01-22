package br.gov.sead.pagrn.dto.agenciaBanco;

import br.gov.sead.pagrn.domain.type.Banco;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AgenciaBancoDtoResponse {

    private Long id;

    private String codigo;

    private String DV;

    private String denominacao;

    private Banco banco;
}
