package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.*;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.FuncaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncaoService extends AbstractService<Funcao, FuncaoRepository> {

    private UnidadeOrganizacionalService unidadeOrganizacionalService;

    private CboService cboService;

    private TipoGratificacaoService tipoGratificacaoService;

    public FuncaoService(FuncaoRepository repository, UnidadeOrganizacionalService unidadeOrganizacionalService,
                         CboService cboService, TipoGratificacaoService tipoGratificacaoService) {
        super(repository);
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
        this.cboService = cboService;
        this.tipoGratificacaoService = tipoGratificacaoService;

    }

    @Transactional
    public Funcao insert(Funcao funcao, Long unidadeOrganizacionalId, Long cboId, Long tipoGratificacoId){

        UnidadeOrganizacional unidadeOrganizacional = unidadeOrganizacionalService
                .findByIdOrThrowException(unidadeOrganizacionalId, ApiMessages.PESSOA_JURIDICA_NAO_ENCONTRADA);

        Cbo cbo = cboService.findByIdOrThrowException(cboId, ApiMessages.CBO_NAO_ENCONTRADO);

        TipoGratificacao tipoGratificacao = tipoGratificacaoService.
                findByIdOrThrowException(tipoGratificacoId, ApiMessages.TIPO_GRATIFICACAO_NAO_ENCONTRADA);

        funcao.setUnidadeOrganizacional(unidadeOrganizacional);
        funcao.setCbo(cbo);
        funcao.setTipoGratificacao(tipoGratificacao);

        return super.create(funcao);
    }

    @Transactional
    public Funcao update(Long funcaoId, Funcao funcao, Long unidadeOrganizacionalId, Long cboId,Long tipoGratificacoId){

        UnidadeOrganizacional unidadeOrganizacional = unidadeOrganizacionalService
                .findByIdOrThrowException(unidadeOrganizacionalId, ApiMessages.PESSOA_JURIDICA_NAO_ENCONTRADA);

        Cbo cbo = cboService.findByIdOrThrowException(cboId, ApiMessages.CBO_NAO_ENCONTRADO);

        TipoGratificacao tipoGratificacao = tipoGratificacaoService.
                findByIdOrThrowException(tipoGratificacoId, ApiMessages.TIPO_GRATIFICACAO_NAO_ENCONTRADA);

        funcao.setUnidadeOrganizacional(unidadeOrganizacional);
        funcao.setCbo(cbo);
        funcao.setTipoGratificacao(tipoGratificacao);

        return super.update(funcaoId, funcao);
    }
}
