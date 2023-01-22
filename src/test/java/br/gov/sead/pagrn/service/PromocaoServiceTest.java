package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.*;
import br.gov.sead.pagrn.domain.events.Promocao;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.*;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PromocaoServiceTest {

    PromocaoRepository promocaoRepository = mock(PromocaoRepository.class);

    VinculoRepository vinculoRepository = mock(VinculoRepository.class);

    SetorService setorService = mock(SetorService.class);

    PessoaJuridicaService pessoaJuridicaService = mock(PessoaJuridicaService.class);

    UnidadeOrganizacionalService uoService = mock(UnidadeOrganizacionalService.class);

    ServidorService servidorService = mock(ServidorService.class);

    VinculoCargoService vinculoCargoService = mock(VinculoCargoService.class);

    VinculoFuncaoService vinculoFuncaoService = mock(VinculoFuncaoService.class);

    VinculoService vinculoService = new VinculoService(vinculoRepository, setorService, pessoaJuridicaService, uoService,
            servidorService, vinculoCargoService, vinculoFuncaoService);

    Vinculo vinculo = mock(Vinculo.class);


    @Test
    void promoverTest(){
        PromocaoService promocaoService = new PromocaoService(promocaoRepository, vinculoService);
        Promocao promocao = new Promocao();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(promocaoRepository.save(any())).thenReturn(promocao);

        assertDoesNotThrow(()-> promocaoService.promover(promocao, 0L, 0L));
    }

    @Test
    void promoverTestNivelCargoNonExistent(){

    PromocaoService promocaoService = new PromocaoService(promocaoRepository, vinculoService);
    Promocao promocao = new Promocao();

    assertThrows(EntityNotFoundException.class, () -> promocaoService.promover(promocao, 0L, 0L));
    }

    @Test
    void promoverTestIdVinculoNonExistent(){
        PromocaoService promocaoService = new PromocaoService(promocaoRepository, vinculoService);
        Promocao promocao = new Promocao();




        assertThrows(EntityNotFoundException.class, () -> promocaoService.promover(promocao, 0L, 0L));
    }
}