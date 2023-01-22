package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Endereco;
import br.gov.sead.pagrn.domain.concrets.Setor;
import br.gov.sead.pagrn.repository.PessoaJuridicaRepository;
import br.gov.sead.pagrn.repository.SetorRepository;
import br.gov.sead.pagrn.repository.UnidadeOrganizacionalRepository;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SetorServiceTest {

    SetorRepository setorRepository = mock(SetorRepository.class);
    UnidadeOrganizacionalRepository unidadeOrganizacionalRepository = mock(UnidadeOrganizacionalRepository.class);

    PessoaJuridicaRepository pessoaJuridicaRepository = mock(PessoaJuridicaRepository.class);

    @Test
    void insertTestIdSetorSuperioNonexisten() {

        Setor setor = new Setor();
        Endereco endereco = new Endereco();

        PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService(pessoaJuridicaRepository);
        UnidadeOrganizacionalService unidadeOrganizacionalService = new UnidadeOrganizacionalService(unidadeOrganizacionalRepository, pessoaJuridicaService);
        SetorService setorService = new SetorService(setorRepository, unidadeOrganizacionalService);

        assertThrows(EntityNotFoundException.class, () -> setorService.insert(setor, 0L, 0L, endereco));
    }

    @Test
    void insertTestIdUnidadeOrganizacionalNonexisten(){
        Setor setor = new Setor();
        Endereco endereco = new Endereco();

        PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService(pessoaJuridicaRepository);
        UnidadeOrganizacionalService unidadeOrganizacionalService = new UnidadeOrganizacionalService(unidadeOrganizacionalRepository, pessoaJuridicaService);
        SetorService setorService = new SetorService(setorRepository, unidadeOrganizacionalService);

        Setor setorSuperior = new Setor();

        when(setorRepository.findById(0L)).thenReturn(Optional.of(setorSuperior));

        assertThrows(EntityNotFoundException.class, () -> setorService.insert(setor, 0L, 0L, endereco));

    }
}