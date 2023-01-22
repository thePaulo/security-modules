package br.gov.sead.motorDeCalculo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class SalarioMinimo extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private BigDecimal valor;

    private LocalDate dataCriacao;

    private LocalDate dataExtincao;

    public SalarioMinimo(String valor, String dataCriacao, String dataExtincao) {
        this.valor = new BigDecimal(valor);
        this.dataCriacao = LocalDate.parse(dataCriacao, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.dataExtincao = LocalDate.parse(dataExtincao, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}
