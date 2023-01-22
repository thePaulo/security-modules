package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.PessoaFisica;
import br.gov.sead.pagrn.domain.events.Falecimento;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.FalecimentoRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FalecimentoServiceTest {

    FalecimentoRepository falecimentoRepository = mock(FalecimentoRepository.class);

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

    PessoaFisicaService pessoaFisicaService = mock(PessoaFisicaService.class);


    @Test
    void FalecimentoTest(){

        FalecimentoService falecimentoService = new FalecimentoService(falecimentoRepository, pessoaFisicaService, vinculoService);
        Falecimento falecimento = new Falecimento();
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.falecer();
        ArrayList vinculoList = new ArrayList<Vinculo>();
        vinculoList.add(vinculo);
        Page<Vinculo> vinculos = new PageImpl<>(vinculoList);

        when(falecimentoRepository.save(any())).thenReturn(falecimento);
        when(pessoaFisicaService.registrarFalecimento(any())).thenReturn(pessoaFisica);
        when(vinculoRepository.findByCPFdoServidor(any(), any())).thenReturn(vinculos);
        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));

        assertDoesNotThrow(()-> falecimentoService.registrarFalecimento(falecimento, 0L));
    }

}