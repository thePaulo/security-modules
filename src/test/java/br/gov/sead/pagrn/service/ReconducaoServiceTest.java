package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Reconducao;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.ReconducaoRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReconducaoServiceTest {

    ReconducaoRepository reconducaoRepository = mock(ReconducaoRepository.class);

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
    void ReconducaoTest(){

        ReconducaoService reconducaoService = new ReconducaoService(reconducaoRepository, vinculoService);
        Reconducao reconducao = new Reconducao();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(reconducaoRepository.save(any())).thenReturn(reconducao);

        assertDoesNotThrow(()-> reconducaoService.reconduzir(reconducao, 0L));
    }

}