package br.gov.sead.pagrn.dto.designacao;

import br.gov.sead.pagrn.dto.funcao.FuncaoDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class DesignacaoDtoResponse {

    private Long id;

    private FuncaoDtoResponse funcao;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
