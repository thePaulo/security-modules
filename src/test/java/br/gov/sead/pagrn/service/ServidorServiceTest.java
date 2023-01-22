package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Servidor;
import br.gov.sead.pagrn.domain.type.Escolaridade;
import br.gov.sead.pagrn.repository.PessoaFisicaRepository;
import br.gov.sead.pagrn.repository.ServidorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServidorServiceTest {


    ServidorRepository servidorRepository = mock(ServidorRepository.class);

    PessoaFisicaRepository pessoaFisicaRepository = mock(PessoaFisicaRepository.class);

    @Test
    void insertTestIdPessoaFisicaNonexistent() {
        PessoaFisicaService pessoaFisicaService = new PessoaFisicaService(pessoaFisicaRepository);
        ServidorService servidorService = new ServidorService(servidorRepository, pessoaFisicaService);
        Servidor servidor = new Servidor();
        servidor.setMatricula("128974");
        servidor.setEscolaridade(Escolaridade.SUPERIOR);
        servidor.setPispasep("we456");

        when(pessoaFisicaRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> servidorService.insert(servidor, Long.valueOf(0)));
    }

}