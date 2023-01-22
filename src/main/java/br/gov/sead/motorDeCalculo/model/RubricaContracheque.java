package br.gov.sead.motorDeCalculo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class RubricaContracheque extends AbstractEntity {

    private String codRubrica;

    private String descricao;

    private BigDecimal valor;

    private LocalDate competencia;
    @ManyToOne
    @JoinColumn(name = "contracheque_id")
    @JsonIgnore
    private Contracheque contracheque;

    public RubricaContracheque(String codRubrica, String descricao, BigDecimal valor, LocalDate competencia) {
        this.codRubrica = codRubrica;
        this.descricao = descricao;
        this.valor = valor;
        this.competencia = competencia;
    }
}
