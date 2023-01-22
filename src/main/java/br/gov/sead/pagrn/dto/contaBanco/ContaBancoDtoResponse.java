package br.gov.sead.pagrn.dto.contaBanco;

import br.gov.sead.pagrn.domain.type.TipoConta;
import br.gov.sead.pagrn.dto.agenciaBanco.AgenciaBancoDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContaBancoDtoResponse {

    private Long id;

    private AgenciaBancoDtoResponse agenciaBanco;

    private String numeroConta;

    private String numeroContaDV;

    private TipoConta tipoConta;
}
