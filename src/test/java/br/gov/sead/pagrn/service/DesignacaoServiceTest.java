package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Funcao;
import br.gov.sead.pagrn.domain.events.Designacao;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.DesignacaoRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DesignacaoServiceTest {

    DesignacaoRepository designacaoRepository = mock(DesignacaoRepository.class);

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

    FuncaoService funcaoService = mock(FuncaoService.class);


    @Test
    void designarTest(){

        DesignacaoService designacaoService = new DesignacaoService(designacaoRepository, vinculoService, funcaoService);
        Designacao designacao = new Designacao();
        Funcao funcao = new Funcao();

        when(funcaoService.findByIdOrThrowException(0L, ApiMessages.FUNCAO_NAO_ENCONTRADA)).thenReturn(funcao);
        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(designacaoRepository.save(any())).thenReturn(designacao);

        assertDoesNotThrow(()-> designacaoService.designar(designacao, 0L, 0L));
    }

}