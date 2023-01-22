package br.gov.sead.motorDeCalculo.repository;

import br.gov.sead.motorDeCalculo.model.Snapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SnapshotRepository extends JpaRepository<Snapshot, Long> {

//    @Query(value = "SELECT new Snapshot(v.id, v.regimeJuridico, c.denominacao, nc.remuneracaoBase, p.nome," +
//            " uo.sigla, setor.denominacao, s.matricula, cast(:vigencia as string)) " +
//            "FROM vinculos v, cargos c, pessoas_fisicas p, cargos_uo c_uo, niveis_cargos nc, servidores s, unidades_organizacionais uo, setores setor " +
//            "WHERE v.nivelCargo.id=nc.id and nc.cargoUO.id=c_uo.id and c_uo.cargo.id=c.id and v.servidor.id=s.id and s.pessoaFisica.id=p.id " +
//            "and v.uoPagante=uo.id and setor.id = v.setor")
//    List<Snapshot> buscarComData(@Param("vigencia") String vigencia);

//    @Query(value = "SELECT new Snapshot(v.id, v.categoria, v.ferias, v.faltas, v.dependentes, v.horasNoturnas, c.nome, " +
//            "c.vencimentoBasico, c.tipo, c.jornada, p.nome, l.insalubre, nc.remuneracaoBase, cast(:vigencia as string), v.dataExercicio) FROM Vinculo v, Cargo c, Pessoa p, Lotacao l, NivelCargo nc WHERE v.cargo.id=c.id and " +
//            "v.pessoa.id=p.id and v.lotacao.id=l.id and v.nivelCargo.id = nc.id")
//    List<Snapshot> buscaComData(@Param("vigencia") String vigencia);
}
