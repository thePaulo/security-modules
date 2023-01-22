package br.gov.sead.pagrn.dto.sancao;

import br.gov.sead.pagrn.domain.type.TipoSancao;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SancaoDtoResponse {

    private Long id;

    private String processoAdministrativo;

    private TipoSancao tipoSancao;

    //evento

    private LocalDate dataRegistro;

    private LocalDate dataVigencia;

    private String descricao;
}

