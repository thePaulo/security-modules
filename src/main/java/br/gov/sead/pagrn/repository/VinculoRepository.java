package br.gov.sead.pagrn.repository;

import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.generic.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VinculoRepository extends GenericRepository<Vinculo> {

    @Query(value = "select * " +
            "from vinculos v " +
            "where v.servidor_id in (select s.id " +
            "                        from servidores s, pessoas_fisicas p " +
            "                        where s.pessoa_fisica_id = p.id " +
            "                        and p.cpf = ?1 " +
            "                        group by s.id) ", nativeQuery = true)
    Page<Vinculo> findByCPFdoServidor(String cpf, Pageable pageable);

    @Query(value = "select v.id " +
            "from vinculos v " +
            "where v.servidor_id in (select s.id " +
            "                        from servidores s, pessoas_fisicas p " +
            "                        where s.pessoa_fisica_id = p.id " +
            "                        and p.cpf = ?1 " +
            "                        group by s.id) ", nativeQuery = true)
    List<Long> findByCPFdoServidor(String cpf);

    @Query(value = "select * " +
            "from vinculos v " +
            "where v.servidor_id in (select s.id " +
            "                        from servidores s, pessoas_fisicas p " +
            "                        where s.pessoa_fisica_id = p.id " +
            "                        and p.nome like CONCAT('%',?1,'%')" +
            "                        group by s.id) ", nativeQuery = true)
    Page<Vinculo> findByNomeDoServidor(String nome, Pageable pageable);

    @Query(value = "select * " +
            "from vinculos v, servidores s " +
            "where v.servidor_id = s.id " +
            "and s.matricula like ?1 ", nativeQuery = true)
    Page<Vinculo> findByMatriculaDoServidor(String matricula, Pageable pageable);
}

