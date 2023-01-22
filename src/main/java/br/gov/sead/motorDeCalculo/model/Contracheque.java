package br.gov.sead.motorDeCalculo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contracheque extends AbstractEntity {

    private String pessoaNome;

    private String matricula;

    private String cargo;

    private String orgao;

    private LocalDate referencia;

    private String setor;

    @OneToMany(mappedBy = "contracheque", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RubricaContracheque> rubricasContracheque;

    private BigDecimal liquido;

    private BigDecimal somatorioVantagens;

    private BigDecimal somatorioDescontos;

    public Contracheque(Long id, String pessoaNome, String matricula, String cargo, String orgao, LocalDate referencia, String setor, BigDecimal liquido, BigDecimal somatorioVantagens, BigDecimal somatorioDescontos) {
        super(id);
        this.pessoaNome = pessoaNome;
        this.matricula = matricula;
        this.cargo = cargo;
        this.orgao = orgao;
        this.referencia = referencia;
        this.setor = setor;
        this.liquido = liquido;
        this.somatorioVantagens = somatorioVantagens;
        this.somatorioDescontos = somatorioDescontos;
    }
}
