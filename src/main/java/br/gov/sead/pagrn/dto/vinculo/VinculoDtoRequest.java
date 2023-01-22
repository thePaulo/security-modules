package br.gov.sead.pagrn.dto.vinculo;

import br.gov.sead.pagrn.domain.type.RegimeJuridico;
import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
import br.gov.sead.pagrn.domain.type.TipoVinculo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VinculoDtoRequest {
    private Long setorId;

    private Long pessoaFisicaContratanteId;

    private Long uoPaganteId;

    private RegimeJuridico regimeJuridico;

    private TipoVinculo tipoVinculo;

    private SituacaoVinculo situacao;

    private Long servidorId;

}
