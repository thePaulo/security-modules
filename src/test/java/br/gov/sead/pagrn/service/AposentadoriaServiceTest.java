package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Aposentadoria;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.AposentadoriaRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AposentadoriaServiceTest {
    AposentadoriaRepository aposentadoriaRepository = mock(AposentadoriaRepository.class);

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
    void aposentarTest(){
        AposentadoriaService aposentadoriaService = new AposentadoriaService(aposentadoriaRepository, vinculoService);
        Aposentadoria aposentadoria = new Aposentadoria();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(aposentadoriaRepository.save(any())).thenReturn(aposentadoria);

        assertDoesNotThrow(()-> aposentadoriaService.aposentar(aposentadoria, 0L));
    }
}
