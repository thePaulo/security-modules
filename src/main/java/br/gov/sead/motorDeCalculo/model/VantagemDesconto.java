package br.gov.sead.motorDeCalculo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.IdClass;

@NamedNativeQuery(name = "VantagemDesconto.buscarSomatorioByVigencia",
                  query = "SELECT vd.cod_rubrica as codRubrica, SUM(vd.valor) as somatorio FROM vantagem_desconto vd WHERE vd.vinculo_id = ?1 AND vd.vigencia >= ?2 AND vd.vigencia < ?3 GROUP BY vd.cod_rubrica",
                  resultSetMapping = "Mapping.RubricaSoma")
@SqlResultSetMapping(name = "Mapping.RubricaSoma",
                     classes = @ConstructorResult(targetClass = RubricaSoma.class,
                                                  columns = {@ColumnResult(name = "codRubrica"),
                                                             @ColumnResult(name = "somatorio")}))
                                                            
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(VantagemDescontoId.class)
public class VantagemDesconto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String codRubrica;

    private BigDecimal valor;

    private LocalDate vigencia;

    public VantagemDesconto(String codRubrica, BigDecimal valor, LocalDate vigencia) {
        this.codRubrica = codRubrica;
        this.valor = valor;
        this.vigencia = vigencia;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "vinculo_id")
    private Snapshot snapshot;

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }
}