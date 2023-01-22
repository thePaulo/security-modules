package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Reversao;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.ReversaoRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReversaoServiceTest {

    ReversaoRepository reversaoRepository = mock(ReversaoRepository.class);

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
    void ReversaoTest(){

        ReversaoService reversaoService = new ReversaoService(reversaoRepository, vinculoService);
        Reversao reversao = new Reversao();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(reversaoRepository.save(any())).thenReturn(reversao);

        assertDoesNotThrow(()-> reversaoService.reverter(reversao, 0L));
    }

}