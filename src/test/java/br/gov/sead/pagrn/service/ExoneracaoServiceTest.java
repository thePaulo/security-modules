package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Exoneracao;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.ExoneracaoRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExoneracaoServiceTest {

    ExoneracaoRepository exoneracaoRepository = mock(ExoneracaoRepository.class);

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
    void exoneracaoTest(){

        ExoneracaoService exoneracaoService = new ExoneracaoService(exoneracaoRepository, vinculoService);
        Exoneracao exoneracao = new Exoneracao();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(exoneracaoRepository.save(any())).thenReturn(exoneracao);

        assertDoesNotThrow(()-> exoneracaoService.exonerar(exoneracao, 0L));
    }

}