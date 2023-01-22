package br.gov.sead.pagrn.dto.vacancia;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class VacanciaDtoRequest {

   private Long vinculo;

   private String processoAdministrativo;

   private LocalDate dataVigencia;

   private String descricao;

}
