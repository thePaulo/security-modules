package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Dependente;
import br.gov.sead.pagrn.domain.type.TipoDependencia;
import br.gov.sead.pagrn.repository.DependenteRepository;
import br.gov.sead.pagrn.repository.PessoaFisicaRepository;
import br.gov.sead.pagrn.repository.ServidorRepository;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DependenteServiceTest {

    DependenteRepository dependenteRepository = mock(DependenteRepository.class);
    PessoaFisicaRepository pessoaFisicaRepository = mock(PessoaFisicaRepository.class);
    ServidorRepository servidorRepository = mock(ServidorRepository.class);

    @Test
    void insertTestIdPessoaFisicaNonexistent(){
        PessoaFisicaService pessoaFisicaService = new PessoaFisicaService(pessoaFisicaRepository);
        ServidorService servidorService = new ServidorService(servidorRepository, pessoaFisicaService);
        DependenteService dependenteService = new DependenteService(dependenteRepository, pessoaFisicaService, servidorService);
        Dependente dependente = new Dependente();
//        dependente.setTipoDependencia(TipoDependencia.PENSAO_ALIMENTICIA);

        when(pessoaFisicaRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->  dependenteService.insert(dependente, 0L, 0L));

    }

    @Test
    void insertTestIdServidorNonexistent(){
        PessoaFisicaService pessoaFisicaService = new PessoaFisicaService(pessoaFisicaRepository);
        ServidorService servidorService = new ServidorService(servidorRepository, pessoaFisicaService);
        DependenteService dependenteService = new DependenteService(dependenteRepository, pessoaFisicaService, servidorService);
        Dependente dependente = new Dependente();
//        dependente.setTipoDependencia(TipoDependencia.PENSAO_ALIMENTICIA);

        when(servidorRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->  dependenteService.insert(dependente, 0L, 0L));

    }
}