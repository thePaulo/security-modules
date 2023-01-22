package br.gov.sead.pagrn.dto.funcao;

import br.gov.sead.pagrn.domain.concrets.Cbo;
import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.domain.type.TipoGratificacao;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FuncaoDtoResponse {

    private Long id;

    private String denominacao;

    private Double valorVencimento;

    private Double valorRepresentacao;

    private TipoGratificacao tipoGratificacao;

    private Cbo cbo;

    private UnidadeOrganizacional unidadeOrganizacional;


}
