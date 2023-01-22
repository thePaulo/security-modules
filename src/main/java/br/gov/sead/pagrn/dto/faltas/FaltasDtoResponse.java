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
public class FaltasDtoResponse {

    private Long id;

    private LocalDate dataInicio;

    private LocalDate dataFinal;

    private String justificativa;

    //evento

    protected LocalDate dataRegistro;

    private LocalDate dataVigencia;

    private String descricao;
}
