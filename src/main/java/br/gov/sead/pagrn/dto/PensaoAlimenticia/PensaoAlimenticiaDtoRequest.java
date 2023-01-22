package br.gov.sead.pagrn.dto.PensaoAlimenticia;

import br.gov.sead.pagrn.domain.type.BaseAlimentos;
import br.gov.sead.pagrn.domain.type.TipoReajuste;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PensaoAlimenticiaDtoRequest {

    private Long dependencia;

    private BaseAlimentos baseAlimentos;

    private TipoReajuste tipoReajuste;

    private Double percentual;

    private Boolean incide13;

    private Boolean incideFeriasAdiantamento;

    private Boolean incideFerias;

    private Boolean incideAbonoPecuario;

    private String observacoes;

    private LocalDate dataInicio;

    private LocalDate dataFinal;
}
