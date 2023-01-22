package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Afastamento;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.AfastamentoRepository;
import br.gov.sead.pagrn.repository.VinculoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AfastamentoServiceTest {

    AfastamentoRepository afastamentoRepository = mock(AfastamentoRepository.class);

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
    void afastarTest(){
        AfastamentoService afastamentoService = new AfastamentoService(afastamentoRepository, vinculoService);
        Afastamento afastamento = new Afastamento();

        when(vinculoRepository.findById(any())).thenReturn(Optional.of(vinculo));
        when(afastamentoRepository.save(any())).thenReturn(afastamento);

        assertDoesNotThrow(()-> afastamentoService.afastar(afastamento, 0L));
    }
}
