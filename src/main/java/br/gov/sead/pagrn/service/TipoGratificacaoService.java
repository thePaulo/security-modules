package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Rubrica;
import br.gov.sead.pagrn.domain.concrets.TipoGratificacao;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.TipoGratificacaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class TipoGratificacaoService extends AbstractService<TipoGratificacao, TipoGratificacaoRepository> {

    private RubricaService rubricaService;

    public TipoGratificacaoService(TipoGratificacaoRepository repository, RubricaService rubricaService){
        super(repository);
        this.rubricaService = rubricaService;

    }

    public TipoGratificacao insert(TipoGratificacao tipoGratificacao, Long idRubrica) {
        Rubrica rubrica = rubricaService.findByIdOrThrowException(idRubrica, ApiMessages.RUBRICA_NAO_ENCONTRADA);

        tipoGratificacao.setRubrica(rubrica);
        return super.create(tipoGratificacao);
    }

    public TipoGratificacao update(Long id, TipoGratificacao tipoGratificacao, Long idRubrica) {
        Rubrica rubrica = rubricaService.findByIdOrThrowException(idRubrica, ApiMessages.RUBRICA_NAO_ENCONTRADA);

        tipoGratificacao.setRubrica(rubrica);
        return super.update(id, tipoGratificacao);
    }


}
