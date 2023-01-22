package br.gov.sead.motorDeCalculo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RubricaDrools {

    @Id
    private String codigo;

    private String nome;

    private String descricao;

    private String tipo;

    private LocalDate dataCriacao;

    private LocalDate dataExtincao;

    private boolean incideImpostoRenda;

    private boolean incideINSS;

    private boolean incideIPE;
}
