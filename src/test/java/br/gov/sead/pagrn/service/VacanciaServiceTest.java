package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Vacancia;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.VacanciaRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VacanciaServiceTest {

    VacanciaRepository vacanciaRepository = mock(VacanciaRepository.class);

    Vinculo vinculo = mock(Vinculo.class);

    VinculoRepository vinculoRepository = mock(VinculoRepository.class);

    SetorService setorService = mock(SetorService.class);

    PessoaJuridicaService pessoaJuridicaService = mock(PessoaJuridicaService.class);

    UnidadeOrganizacionalService uoService = mock(UnidadeOrganizacionalService.class);

    ServidorService servidorService = mock(ServidorService.class);

    VinculoCargoService vinculoCargoService = mock(VinculoCargoService.class);

    VinculoFuncaoService vinculoFuncaoService = mock(VinculoFuncaoService.class);

    VinculoService vinculoService = new VinculoService(vinculoRepository, setorService, pessoaJuridicaService, uoService,
            servidorService, vinculoCargoService, vinculoFuncaoService);


    @Test
    void VacanciaTest(){

        VacanciaService vacanciaService = new VacanciaService(vacanciaRepository, vinculoService);
        Vacancia vacancia = new Vacancia();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(vacanciaRepository.save(any())).thenReturn(vacancia);

        assertDoesNotThrow(()-> vacanciaService.suspender(vacancia, 0L));
    }
}