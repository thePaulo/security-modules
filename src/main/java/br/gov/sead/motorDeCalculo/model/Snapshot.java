package br.gov.sead.motorDeCalculo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import br.gov.sead.pagrn.domain.type.RegimeJuridico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Snapshot implements Serializable{

    private static final long serialVersionUID = 1L;
   
    @Id
    private Long vinculoId;
    private String vinculoRegime;
    private String cargoNome;
    private BigDecimal remuneracaoBase;
    private String pessoaNome;
    private String orgao;
    private String setor;
    private String matricula;
    private LocalDate vigencia;

    private BigDecimal bruto = new BigDecimal("0");

    private BigDecimal liquido = new BigDecimal("0");

    private BigDecimal somatorioIRRF = new BigDecimal("0");

    private BigDecimal somatorioINSS = new BigDecimal("0");

    private BigDecimal somatorioDescontos = new BigDecimal("0");

    private BigDecimal somatorioIPE = new BigDecimal("0");

    @OneToMany(mappedBy = "snapshot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VantagemDesconto> vantagemDescontos;

    public void addVantagemDesconto(VantagemDesconto novo){
        vantagemDescontos.add(novo);
        novo.setSnapshot(this);
    }

    public void removeVantagemDesconto(VantagemDesconto alvo){
        vantagemDescontos.remove(alvo);
        alvo.setSnapshot(null);
    }

    public Snapshot(Long vinculoId, String cargoNome, BigDecimal remuneracaoBase, String pessoaNome) {
        this.vinculoId = vinculoId;
        this.cargoNome = cargoNome;
        this.remuneracaoBase = remuneracaoBase;
        this.pessoaNome = pessoaNome;
        this.vantagemDescontos = new ArrayList<>();
    }

    public Snapshot(Long vinculoId, RegimeJuridico vinculoRegime, String cargoNome, BigDecimal remuneracaoBase, String pessoaNome,
                    String orgao, String setor, String matricula, String vigencia) {
        this.vinculoId = vinculoId;
        this.vinculoRegime = vinculoRegime.getDescricao();
        this.cargoNome = cargoNome;
        this.remuneracaoBase = remuneracaoBase;
        this.pessoaNome = pessoaNome;
        this.orgao = orgao;
        this.setor = setor;
        this.matricula = matricula;
        this.vigencia = LocalDate.parse(vigencia, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.vantagemDescontos = new ArrayList<>();
    }

    public Boolean verificaVigencia(String dataInicial, String dataFinal){
        return vigencia.isAfter(LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("dd-MM-yyyy"))) && vigencia.isBefore(LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

}
