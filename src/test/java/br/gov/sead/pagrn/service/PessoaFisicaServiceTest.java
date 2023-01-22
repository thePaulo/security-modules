package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Deficiencia;
import br.gov.sead.pagrn.domain.concrets.PessoaFisica;
import br.gov.sead.pagrn.domain.type.EstadoCivil;
import br.gov.sead.pagrn.domain.type.Genero;
import br.gov.sead.pagrn.domain.type.TipoSanguineo;
import br.gov.sead.pagrn.repository.PessoaFisicaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PessoaFisicaServiceTest {

    @Mock
    PessoaFisicaRepository pessoaFisicaRepository;
    @InjectMocks
    PessoaFisicaService pessoaFisicaService;
    @Test
    public void insertTest() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Acsa Laiane");
        pessoaFisica.setCpf("32910870030");
        pessoaFisica.setEmail("string@gmail.com");
        pessoaFisica.setGenero(Genero.FEMININO);
        pessoaFisica.setDataNascimento(LocalDate.EPOCH);
        pessoaFisica.setTipoSanguineo(TipoSanguineo.Op);
        pessoaFisica.setNomePai("David Augusto");
        pessoaFisica.setNomeMae("Dagmar Arcanjo");
        pessoaFisica.setNacionalidadePais("Brasileiro");
        pessoaFisica.setNaturalidadeCidade("Natal");
        pessoaFisica.setEstadoCivil(EstadoCivil.SOLTEIRO);

        when(pessoaFisicaRepository.save(pessoaFisica)).thenReturn(pessoaFisica);

        assertEquals(pessoaFisica, pessoaFisicaService.insert(pessoaFisica));
    }
}