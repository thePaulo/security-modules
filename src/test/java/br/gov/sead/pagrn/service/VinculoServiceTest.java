//package br.gov.sead.pagrn.service;
//
//import br.gov.sead.pagrn.domain.concrets.*;
//import br.gov.sead.pagrn.domain.events.*;
//import br.gov.sead.pagrn.domain.type.RegimeJuridico;
//import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
//import br.gov.sead.pagrn.domain.type.TipoVinculo;
//import br.gov.sead.pagrn.domain.vinculos.Vinculo;
//import br.gov.sead.pagrn.repository.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class VinculoServiceTest {
//
//    VinculoRepository vinculoRepository = mock(VinculoRepository.class);
//
//    SetorService setorService = mock(SetorService.class);
//
//    PessoaJuridicaService pessoaJuridicaService = mock(PessoaJuridicaService.class);
//
//    UnidadeOrganizacionalService uoService = mock(UnidadeOrganizacionalService.class);
//
//    VinculoService vinculoService = new VinculoService(vinculoRepository, setorService, pessoaJuridicaService, uoService);
//
//
//    private void getNomeacao(TipoVinculo tipoVinculo, ProvimentoDeCargo nomeacao) {
//
//        nomeacao.setTipoVinculo(tipoVinculo);
//        nomeacao.setRegimeJuridico(RegimeJuridico.ESTATUTARIO);
//        nomeacao.setDataNomeacao(LocalDate.now());
//        nomeacao.setDataPosse(LocalDate.now());
//
//
//
//    }
//
//    @ParameterizedTest
//    @CsvSource({"EFETIVO_CIVIL", "EFETIVO_MILITAR"})
//    void testVinculoEfetivoAposentarEReverter(TipoVinculo tipoVinculo) {
//
//        ProvimentoDeCargo nomeacao = new ProvimentoDeCargo();
//        getNomeacao(tipoVinculo, nomeacao);
//        Vinculo vinculo = vinculoService.criarVinculo(nomeacao);
//
//        assertEquals(SituacaoVinculo.ATIVO, vinculo.getSituacao());
//
//        Aposentadoria aposentadoria = new Aposentadoria();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculo));
//        Vinculo vinculoAposentado = vinculoService.registrarAposentadoria(aposentadoria, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.APOSENTADO, vinculoAposentado.getSituacao());
//
//        Reversao reversao = new Reversao();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculo));
//        Vinculo vinculoRevertido = vinculoService.registrarReversao(reversao, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.ATIVO, vinculoRevertido.getSituacao());
//    }
//    @ParameterizedTest
//    @CsvSource({"EFETIVO_CIVIL", "EFETIVO_MILITAR"})
//    void testVinculoEfetivoEmDisposicaoEAproveitamento(TipoVinculo tipoVinculo) {
//
//        ProvimentoDeCargo nomeacao = new ProvimentoDeCargo();
//        getNomeacao(tipoVinculo, nomeacao);
//        Vinculo vinculo = vinculoService.criarVinculo(nomeacao);
//
//        Sancao sancao = new Sancao();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculo));
//        Vinculo vinculoEmDisposicao = vinculoService.colocarEmDisponibilidade(sancao, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.EM_DISPOSICAO, vinculoEmDisposicao.getSituacao());
//
//        Aproveitamento aproveitamento = new Aproveitamento();
//        aproveitamento.setSetor(new Setor());
//        aproveitamento.setDataInicialExercicio(LocalDate.now());
//        aproveitamento.setDataPosse(LocalDate.now());
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculoEmDisposicao));
//        Vinculo vinculoAproveitado = vinculoService.aproveitarServidorEmOutroCargo(aproveitamento, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.ATIVO, vinculoAproveitado.getSituacao());
//
//    }
//
//    @ParameterizedTest
//    @CsvSource({"EFETIVO_CIVIL", "EFETIVO_MILITAR"})
//    void testVinculoEfetivoAfastamentoERetorno(TipoVinculo tipoVinculo) {
//
//        ProvimentoDeCargo nomeacao = new ProvimentoDeCargo();
//        getNomeacao(tipoVinculo, nomeacao);
//        Vinculo vinculo = vinculoService.criarVinculo(nomeacao);
//
//        Afastamento afastemento = new Afastamento();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculo));
//        Vinculo vinculoAfastado = vinculoService.registrarAfastamento(afastemento, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.AFASTADO, vinculoAfastado.getSituacao());
//
//        Retorno retorno = new Retorno();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculoAfastado));
//        Vinculo vinculoRetorno = vinculoService.registrarRetorno(retorno, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.ATIVO, vinculoRetorno.getSituacao());
//    }
//
//    @ParameterizedTest
//    @CsvSource({"EFETIVO_CIVIL", "EFETIVO_MILITAR"})
//    void testVinculoEfetivoVacanciaReconducaoExoneracao(TipoVinculo tipoVinculo){
//
//        ProvimentoDeCargo nomeacao = new ProvimentoDeCargo();
//        getNomeacao(tipoVinculo, nomeacao);
//        Vinculo vinculo = vinculoService.criarVinculo(nomeacao);
//
//        Vacancia vacancia = new Vacancia();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculo));
//        Vinculo vinculoSuspenso = vinculoService.registrarVacancia(vacancia, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.SUSPENSO, vinculoSuspenso.getSituacao());
//
//        Reconducao reconducao = new Reconducao();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculoSuspenso));
//        Vinculo vinculoReconduzido = vinculoService.registrarReconducao(reconducao, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.ATIVO, vinculoReconduzido.getSituacao());
//
//        vinculoService.registrarVacancia(vacancia, vinculo.getId());
//        Exoneracao exoneracao = new Exoneracao();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculo));
//        Vinculo vinculoExonerado = vinculoService.destituirFuncaoOuCargo(exoneracao, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.EXONERADO, vinculoExonerado.getSituacao());
//
//    }
//
//    @ParameterizedTest
//    @CsvSource({"TEMPORARIO"})
//    void testVinculoTemporarioEncerramento(TipoVinculo tipoVinculo){
//
//        ProvimentoDeCargo nomeacao = new ProvimentoDeCargo();
//        getNomeacao(tipoVinculo, nomeacao);
//        nomeacao.setDataFinalExercicio(LocalDate.MAX);
//        Vinculo vinculo = vinculoService.criarVinculo(nomeacao);
//
//        Encerramento encerramento = new Encerramento();
//        when(vinculoRepository.findById(vinculo.getId())).thenReturn(Optional.of(vinculo));
//        Vinculo vinculoEncerrado = vinculoService.encerrarContratoTemporario(encerramento, vinculo.getId());
//
//        assertEquals(SituacaoVinculo.ENCERRADO, vinculoEncerrado.getSituacao());
//
//    }
//
//    @ParameterizedTest
//    @CsvSource({"EFETIVO_CIVIL", "EFETIVO_MILITAR"})
//    void testCriarVinculoEfetivoDatasInvalidas(TipoVinculo tipoVinculo) {
//        ProvimentoDeCargo nomeacao = new ProvimentoDeCargo();
//        getNomeacao(tipoVinculo, nomeacao);
//        nomeacao.setDataNomeacao(LocalDate.of(2023,3,1));
//        nomeacao.setDataPosse(LocalDate.of(2022,4,3));
//        nomeacao.setDataInicioExercicio(LocalDate.EPOCH);
//
//        assertThrows(IllegalArgumentException.class, ()-> vinculoService.criarVinculo(nomeacao));
//
//        nomeacao.setDataNomeacao(LocalDate.of(2022,3,1));
//
//        assertThrows(IllegalArgumentException.class, ()-> vinculoService.criarVinculo(nomeacao));
//
//        nomeacao.setDataInicioExercicio(LocalDate.EPOCH);
//
//        assertThrows(IllegalArgumentException.class, ()-> vinculoService.criarVinculo(nomeacao));
//
//    }
//
//    @ParameterizedTest
//    @CsvSource({"TEMPORARIO"})
//    void testCriarVinculoTemporarioDatasInvalidas(TipoVinculo tipoVinculo) {
//        VinculoService vinculoService = new VinculoService(vinculoRepository);
//        ProvimentoDeCargo nomeacao = new ProvimentoDeCargo();
//        getNomeacao(tipoVinculo, nomeacao);
//        nomeacao.setDataNomeacao(LocalDate.of(2022,3,1));
//        nomeacao.setDataPosse(LocalDate.of(2022,4,3));
//        nomeacao.setDataInicioExercicio(LocalDate.of(2022, 4,4));
//
//        assertThrows(IllegalArgumentException.class, ()-> vinculoService.criarVinculo(nomeacao));
//
//        assertThrows(IllegalArgumentException.class, ()-> vinculoService.criarVinculo(nomeacao));
//
//    }
//    @Test
//    void atribuirFuncao() {
//    }
//
//    @Test
//    void registrarDemissao() {
//    }
//
//    @Test
//    void registrarReintegracao() {
//    }
//
//    @Test
//    void registrarFalecimento() {
//    }
//
//    @Test
//    void progredirCargo() {
//    }
//}