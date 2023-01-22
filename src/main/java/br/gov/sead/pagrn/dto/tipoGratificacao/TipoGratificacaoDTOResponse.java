package br.gov.sead.pagrn.dto.tipoGratificacao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class TipoGratificacaoDTOResponse {

    private Long id;

    private String denominacao;

    private BigDecimal valorVencimento;

    private BigDecimal valorRepresentacao;

    private String mneumonico;

    private LocalDate dataCriacao;

    private LocalDate dataExtincao;

    private Long rubricaId;
}
