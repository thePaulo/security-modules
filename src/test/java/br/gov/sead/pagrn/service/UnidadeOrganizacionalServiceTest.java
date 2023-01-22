package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.repository.PessoaJuridicaRepository;
import br.gov.sead.pagrn.repository.UnidadeOrganizacionalRepository;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnidadeOrganizacionalServiceTest {
    PessoaJuridicaRepository pessoaJuridicaRepository = mock(PessoaJuridicaRepository.class);
    UnidadeOrganizacionalRepository unidadeOrganizacionalRepository = mock(UnidadeOrganizacionalRepository.class);

    @Test
    void insertTestIdPessoaJuridicaNonexistent() {
        PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService(pessoaJuridicaRepository);
        UnidadeOrganizacionalService UOService = new UnidadeOrganizacionalService(unidadeOrganizacionalRepository, pessoaJuridicaService);

        UnidadeOrganizacional UO = new UnidadeOrganizacional();

        when(pessoaJuridicaRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->  UOService.insert(UO, 0L));
    }

}