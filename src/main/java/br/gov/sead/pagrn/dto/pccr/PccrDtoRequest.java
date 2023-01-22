package br.gov.sead.pagrn.dto.pccr;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PccrDtoRequest {
    
    private String denominacao;

    private LocalDate dataVigencia;

    private LocalDate dataExtincao;

    private List<Long> unidadesOrganizacionaisId;
}
