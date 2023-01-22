package br.gov.sead.pagrn.dto.faltas;

import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class FaltasDtoRequest {

    private Long vinculoId;

    private LocalDate dataInicio;

    private LocalDate dataFinal;

    private String justificativa;

    //evento

    private LocalDate dataVigencia;

    private String descricao;
}
