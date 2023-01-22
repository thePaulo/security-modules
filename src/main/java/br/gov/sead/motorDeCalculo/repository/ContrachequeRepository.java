package br.gov.sead.motorDeCalculo.repository;

import br.gov.sead.motorDeCalculo.model.Contracheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContrachequeRepository extends JpaRepository<Contracheque, Long> {

    @Query(value = "SELECT new Contracheque (s.vinculoId, s.pessoaNome, s.matricula, s.cargoNome, s.orgao,  s.vigencia, " +
                                            "s.setor, s.liquido, s.bruto, s.somatorioDescontos) FROM Snapshot s " +
                                            "WHERE s.matricula = :matricula")
    Contracheque buscarContracheque(String matricula);
}
