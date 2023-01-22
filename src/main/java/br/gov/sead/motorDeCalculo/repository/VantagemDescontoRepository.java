package br.gov.sead.motorDeCalculo.repository;

import br.gov.sead.motorDeCalculo.model.RubricaSoma;
import br.gov.sead.motorDeCalculo.model.VantagemDesconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface VantagemDescontoRepository extends JpaRepository<VantagemDesconto, Long> {

    @Query(value = "SELECT * FROM vantagem_desconto vd WHERE vd.vinculo_id = ?1", nativeQuery = true)
    List<VantagemDesconto> buscarContrachequeById(Long vinculoId);

    @Query(value = "SELECT * FROM vantagem_desconto vd WHERE vd.vinculo_id = ?1 AND vd.vigencia >= ?2 AND vd.vigencia < ?3", nativeQuery = true)
    List<VantagemDesconto> buscarContrachequeByVigencia(Long vinculoId, LocalDate inicio, LocalDate fim);

    @Query(nativeQuery = true)
    List<RubricaSoma> buscarSomatorioByVigencia(Long vinculoId, LocalDate inicio, LocalDate fim);

    @Query(value = "SELECT SUM(vd.valor) AS somatorio FROM vantagem_desconto vd, rubrica_drools r WHERE r.codigo = vd.cod_rubrica and  " +
            "  vd.vinculo_id = ?1 AND r.incide_imposto_renda = true AND vd.vigencia = ?2", nativeQuery = true)
    BigDecimal somatorioIRRF(Long vinculoId, LocalDate vigencia);

    @Query(value = "SELECT SUM(vd.valor) AS somatorio FROM vantagem_desconto vd, rubrica_drools r WHERE r.codigo = vd.cod_rubrica and  " +
            "  vd.vinculo_id = ?1 AND r.incideinss = true AND vd.vigencia = ?2", nativeQuery = true)
    BigDecimal somatorioINSS(Long vinculoId, LocalDate vigencia);

    @Query(value = "SELECT SUM(vd.valor) AS somatorio FROM vantagem_desconto vd, rubrica_drools r WHERE r.codigo = vd.cod_rubrica and  " +
            "  vd.vinculo_id = ?1 AND r.incideipe = true AND vd.vigencia = ?2", nativeQuery = true)
    BigDecimal somatorioIPE(Long vinculoId, LocalDate vigencia);

    @Query(value = "SELECT SUM(vd.valor) AS bruto FROM vantagem_desconto vd, rubrica_drools r WHERE r.codigo = vd.cod_rubrica and " +
            "r.tipo = 'Vantagem' and vd.vinculo_id = ?1 and vd.vigencia = ?2", nativeQuery = true)
    BigDecimal salarioBruto(Long vinculoId, LocalDate vigencia);

    @Query(value = "SELECT SUM(vd.valor) AS bruto FROM vantagem_desconto vd, rubrica_drools r WHERE r.codigo = vd.cod_rubrica and " +
            "r.tipo = 'Desconto' and vd.vinculo_id = ?1 and vd.vigencia = ?2", nativeQuery = true)
    BigDecimal somatorioDescontos(Long vinculoId, LocalDate vigencia);

}
