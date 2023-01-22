package br.gov.sead.motorDeCalculo.repository;

import br.gov.sead.motorDeCalculo.model.RubricaDrools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RubricaDroolsRepository extends JpaRepository<RubricaDrools, Long> {
    @Query("SELECT r FROM RubricaDrools r WHERE r.codigo = :codigo")
    RubricaDrools buscarPorCodigo(String codigo);
}
