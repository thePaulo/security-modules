package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Demissao;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.DemissaoRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DemissaoServiceTest {
    DemissaoRepository demissaoRepository = mock(DemissaoRepository.class);

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
    void demitirTest(){
        DemissaoService demissaoService = new DemissaoService(demissaoRepository, vinculoService);
        Demissao demissao = new Demissao();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(demissaoRepository.save(any())).thenReturn(demissao);

        assertDoesNotThrow(()-> demissaoService.demitir(demissao, 0L));
    }

}