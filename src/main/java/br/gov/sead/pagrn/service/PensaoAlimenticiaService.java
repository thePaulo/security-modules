package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.PensaoAlimenticia;
import br.gov.sead.pagrn.domain.concrets.Dependencia;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.PensaoAlimenticiaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class PensaoAlimenticiaService extends AbstractService<PensaoAlimenticia, PensaoAlimenticiaRepository> {

    DependenciaService dependenciaService;
    /**
     * Construtor da classe
     *
     * @param repository
     */
    public PensaoAlimenticiaService(PensaoAlimenticiaRepository repository, DependenciaService dependenciaService) {
        super(repository);
        this.dependenciaService = dependenciaService;
    }

    public PensaoAlimenticia insert(Long idDependencia, PensaoAlimenticia pensao) {

        Dependencia dependencia = dependenciaService.findByIdOrThrowException(idDependencia, ApiMessages.DEPENDENCIA_NAO_ENCONTRADA);

        pensao.preencher(dependencia);

        return super.create(pensao);
    }
}
