package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Funcao;
import br.gov.sead.pagrn.domain.events.Designacao;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.DesignacaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DesignacaoService extends AbstractService<Designacao, DesignacaoRepository> {
    private final FuncaoService funcaoService;
    private final VinculoService vinculoService;


    public DesignacaoService(DesignacaoRepository repository, VinculoService vinculoService, FuncaoService funcaoService){
        super(repository);
        this.funcaoService = funcaoService;
        this.vinculoService = vinculoService;
    }

    /*
     * Método para deginar um servidor para uma função. Um servidor que já exerça uma função não
     * pode ser designado.
     */
    @Transactional
    public Designacao designar(Designacao designacao, Long idVinculo, Long idFuncao){
        Funcao funcao = funcaoService.findByIdOrThrowException(idFuncao, ApiMessages.FUNCAO_NAO_ENCONTRADA);

        designacao.preencher(funcao);
        Designacao designacaoSaved = super.create(designacao);

//        vinculoService.atribuirFuncao(designacaoSaved, idVinculo);
        return designacaoSaved;
    }
}
