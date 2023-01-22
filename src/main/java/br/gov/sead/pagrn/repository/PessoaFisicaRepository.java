package br.gov.sead.pagrn.repository;

import br.gov.sead.pagrn.domain.concrets.PessoaFisica;
import br.gov.sead.pagrn.repository.generic.GenericRepository;

import java.util.Optional;


public interface PessoaFisicaRepository extends GenericRepository<PessoaFisica> {
    Optional<PessoaFisica> findByCpf(String cpf);
}
