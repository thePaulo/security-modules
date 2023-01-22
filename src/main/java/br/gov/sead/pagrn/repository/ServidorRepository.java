package br.gov.sead.pagrn.repository;

import br.gov.sead.pagrn.domain.concrets.Servidor;
import br.gov.sead.pagrn.repository.generic.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServidorRepository extends GenericRepository<Servidor> {
    Optional<Servidor> findByMatricula(String matricula);

    @Query(value = "select * " +
            "       from servidores s, pessoas_fisicas p " +
            "       where s.pessoa_fisica_id = p.id " +
            "       and p.cpf like ?1 ", nativeQuery = true)
    Page<Servidor> findByCpf(String cpf, Pageable pageable);

    @Query(value = "select * " +
            "       from servidores s, pessoas_fisicas p " +
            "       where s.pessoa_fisica_id = p.id " +
            "       and p.nome like ?1 ", nativeQuery = true)
    Page<Servidor> findByNome(String nome, Pageable pageable);


    @Query(value = "SELECT vinculos.id " +
            "FROM vinculos " +
            "INNER JOIN servidores " +
            "ON vinculos.servidor_id = servidores.id and servidores.id = ?1", nativeQuery = true)
    List<Long> findAllVinculosIds(Long idServidor);
}
