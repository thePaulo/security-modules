package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.service.generic.AbstractService;
import br.gov.sead.pagrn.domain.concrets.Carreira;
import br.gov.sead.pagrn.domain.concrets.PCCR;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.CarreiraRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarreiraService extends AbstractService<Carreira, CarreiraRepository> {

    private PCCRService pccrService;

    public CarreiraService(CarreiraRepository repository, PCCRService pccrService) {
        super(repository);
        this.pccrService = pccrService;
    }

    @Transactional
    public Carreira criarCarreira(Carreira carreira, Long pccrId){

        PCCR pccr = pccrService.findByIdOrThrowException(pccrId, ApiMessages.PCCR_NAO_ENCONTRADO);

        carreira.setPccr(pccr);

        return super.create(carreira);
    }

    @Transactional
    public Carreira atualizarCarreira(Long id, Carreira carreira, Long pccrId){

        PCCR pccr = pccrService.findByIdOrThrowException(pccrId, ApiMessages.PCCR_NAO_ENCONTRADO);

        carreira.setPccr(pccr);

        return super.update(id, carreira);
    }
}