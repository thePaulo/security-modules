package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Retorno;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.RetornoRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RetornoServiceTest {

    RetornoRepository retornoRepository = mock(RetornoRepository.class);

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
    void RetornoTest(){

        RetornoService retornoService = new RetornoService(retornoRepository, vinculoService);
        Retorno retorno = new Retorno();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(retornoRepository.save(any())).thenReturn(retorno);

        assertDoesNotThrow(()-> retornoService.retornarAoCargoOuFuncao(retorno, 0L));
    }

}