package br.gov.sead.pagrn.dto.contaBanco;

import br.gov.sead.pagrn.domain.type.TipoConta;
import br.gov.sead.pagrn.dto.agenciaBanco.AgenciaBancoDtoRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContaBancoDtoRequest {

    private AgenciaBancoDtoRequest agenciaBanco;

    private String numeroConta;

    private String numeroContaDV;

    private TipoConta tipoConta;

}
