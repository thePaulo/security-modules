package br.gov.sead.pagrn.dto.aposentadoria;

import br.gov.sead.pagrn.domain.type.TipoAposentadoria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AposentadoriaDtoResponse{

    //Aposentadoria

    private Long id;

    private TipoAposentadoria tipoAposentadoria;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;

}
