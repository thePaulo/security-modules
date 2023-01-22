package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.*;
import br.gov.sead.pagrn.domain.events.Provimento;
import br.gov.sead.pagrn.domain.events.ProvimentoDeCargo;
import br.gov.sead.pagrn.domain.events.ProvimentoDeFuncao;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.ProvimentoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class ProvimentosService extends AbstractService<Provimento, ProvimentoRepository> {
    private final VinculoService vinculoService;
    private final PessoaJuridicaService pessoaJuridicaService;
    private final UnidadeOrganizacionalService unidadeOrganizacionalService;
    private final RemuneracaoBasicaService remuneracaoBasicaService;
    private final FuncaoService funcaoService;


    public ProvimentosService(ProvimentoRepository repository, VinculoService vinculoService,
                              PessoaJuridicaService pessoaJuridicaService,
                              UnidadeOrganizacionalService unidadeOrganizacionalService,
                              RemuneracaoBasicaService remuneracaoBasicaService, FuncaoService funcaoService) {
        super(repository);
        this.vinculoService = vinculoService;
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
        this.pessoaJuridicaService = pessoaJuridicaService;
        this.remuneracaoBasicaService = remuneracaoBasicaService;
        this.funcaoService = funcaoService;
    }

    @Transactional
    public ProvimentoDeCargo proverCargo(ProvimentoDeCargo provimentoDeCargo, Long vinculoId,
                                         Long pessoaJuridicaContratanteId, Long uoPaganteId, Long remuneracaoBasicaId) {
        Vinculo vinculo = vinculoService.findByIdOrThrowException(vinculoId, ApiMessages.VINCULO_NAO_ENCONTRADO);

        PessoaJuridica pessoaJuridicaContratante = pessoaJuridicaService
                .findByIdOrThrowException(pessoaJuridicaContratanteId, ApiMessages.PESSOA_JURIDICA_NAO_ENCONTRADA);

        UnidadeOrganizacional uoPagante = unidadeOrganizacionalService
                .findByIdOrThrowException(uoPaganteId, ApiMessages.UNIDADE_ORGANIZACIONAL_NAO_ENCONTRADO);

        RemuneracaoBasica remuneracaoBasica = remuneracaoBasicaService
                .findByIdOrThrowException(remuneracaoBasicaId, ApiMessages.REMUNERACAO_BASICA_NAO_ENCONTRADA);

        provimentoDeCargo.preencher(pessoaJuridicaContratante, uoPagante, remuneracaoBasica);
        vinculoService.proverCargo(provimentoDeCargo, vinculo);
        return (ProvimentoDeCargo) super.create(provimentoDeCargo);
    }

    @Transactional
    public ProvimentoDeFuncao proverFuncao(ProvimentoDeFuncao provimentoDeFuncao, Long vinculoId, Long pessoaJuridicaContratanteId, Long uoPaganteId, Long funcaoId) {
        Vinculo vinculo = vinculoService.findByIdOrThrowException(vinculoId, ApiMessages.VINCULO_NAO_ENCONTRADO);

        PessoaJuridica pessoaJuridicaContratante = pessoaJuridicaService
                .findByIdOrThrowException(pessoaJuridicaContratanteId, ApiMessages.PESSOA_JURIDICA_NAO_ENCONTRADA);

        UnidadeOrganizacional uoPagante = unidadeOrganizacionalService
                .findByIdOrThrowException(uoPaganteId, ApiMessages.UNIDADE_ORGANIZACIONAL_NAO_ENCONTRADO);

        Funcao funcao = funcaoService.findByIdOrThrowException(funcaoId, ApiMessages.FUNCAO_NAO_ENCONTRADA);

        provimentoDeFuncao.preencher(pessoaJuridicaContratante, uoPagante, funcao);

        vinculoService.proverFuncao(provimentoDeFuncao, vinculo);
        return (ProvimentoDeFuncao) super.create(provimentoDeFuncao);
    }
}