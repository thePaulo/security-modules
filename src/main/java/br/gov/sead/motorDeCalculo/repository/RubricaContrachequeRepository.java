package br.gov.sead.motorDeCalculo.repository;

import br.gov.sead.motorDeCalculo.model.RubricaContracheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RubricaContrachequeRepository extends JpaRepository<RubricaContracheque, Long> {

    @Query(value = "SELECT new RubricaContracheque (v.codRubrica, r.descricao, v.valor, v.vigencia) " +
                    "from RubricaDrools r, VantagemDesconto v, Snapshot s " +
                    "WHERE v.codRubrica=r.codigo and s.vinculoId=:vinculoId")
    List<RubricaContracheque> buscarRubricasContracheque(Long vinculoId);
}
