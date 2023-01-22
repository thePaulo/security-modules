package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Faltas;
import br.gov.sead.pagrn.domain.events.ProvimentoDeCargo;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.FaltasRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FaltasService extends AbstractService<Faltas, FaltasRepository> {

    VinculoService vinculoService;

    /**
     * Construtor da classe
     *
     * @param repository
     */
    public FaltasService(FaltasRepository repository, VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Faltas insert(Faltas faltas, Long vinculoId ) {
        Vinculo vinculo = vinculoService.findByIdOrThrowException(vinculoId, ApiMessages.VINCULO_NAO_ENCONTRADO);
        faltas.preencher();
        vinculoService.registrarFaltas(faltas, vinculo);
        return super.create(faltas);
    }
}
