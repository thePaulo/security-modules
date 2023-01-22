package br.gov.sead.pagrn.dto.aproveitamento;

import br.gov.sead.pagrn.dto.setor.SetorDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AproveitamentoDtoResponse{

    private Long id;

    //aproveitamento
    private LocalDate dataExercicio;

    private LocalDate dataPosse;

    private LocalDate dataAproveitamento;

    private SetorDtoResponse setor;

    private String processoAdministrativo;

    //evento
    private LocalDate dataRegistro;

    private LocalDate dataVigencia;

    private String descricao;

}
