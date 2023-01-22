package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.service.generic.AbstractService;
import br.gov.sead.pagrn.domain.concrets.GrupoOcupacional;
import br.gov.sead.pagrn.domain.concrets.PCCR;
import br.gov.sead.pagrn.domain.concrets.Rubrica;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.GrupoOcupacionalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoOcupacionalService extends AbstractService<GrupoOcupacional, GrupoOcupacionalRepository> {

    private PCCRService pccrService;
    private RubricaService rubricaService;

    public GrupoOcupacionalService(GrupoOcupacionalRepository repository, PCCRService pccrService, RubricaService rubricaService) {
        super(repository);
        this.pccrService = pccrService;
        this.rubricaService = rubricaService;
    }

    @Transactional
    public GrupoOcupacional criarGrupo(GrupoOcupacional grupoOcupacional, Long pccrId, Long rubricaId){
        
        PCCR pccr = pccrService.findByIdOrThrowException(pccrId, ApiMessages.PCCR_NAO_ENCONTRADO);
        Rubrica rubrica = rubricaService.findByIdOrThrowException(rubricaId, ApiMessages.RUBRICA_NAO_ENCONTRADA);

        grupoOcupacional.setPccr(pccr);
        grupoOcupacional.setRubrica(rubrica);

        return super.create(grupoOcupacional);
    }

    @Transactional
    public GrupoOcupacional atualizarGrupo(Long id, GrupoOcupacional grupoOcupacional, Long pccrId, Long rubricaId){
        
        PCCR pccr = pccrService.findByIdOrThrowException(pccrId, ApiMessages.PCCR_NAO_ENCONTRADO);
        Rubrica rubrica = rubricaService.findByIdOrThrowException(rubricaId, ApiMessages.RUBRICA_NAO_ENCONTRADA);

        grupoOcupacional.setPccr(pccr);
        grupoOcupacional.setRubrica(rubrica);

        return super.update(id, grupoOcupacional);
    }
}