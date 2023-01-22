package br.gov.sead.pagrn.dto.funcao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FuncaoDtoRequest {
    private Long VinculoResponsavelId;

    private String denominacao;

    private Double valorVencimento;

    private Double valorRepresentacao;

    private Long tipoGratificacaoId;

    private Long unidadeOrganizacionalId;

    private Long cboId;



}
