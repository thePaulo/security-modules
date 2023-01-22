package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Dependencia;
import br.gov.sead.pagrn.domain.concrets.Dependente;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.DependenciaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class DependenciaService extends AbstractService<Dependencia, DependenciaRepository> {

    private DependenteService dependenteService;

    /**
     * Construtor da classe
     *
     * @param repository
     */
    public DependenciaService(DependenciaRepository repository, DependenteService dependenteService) {
        super(repository);
        this.dependenteService = dependenteService;
    }

    public Dependencia insert(Dependencia dependencia, Long idDependente) {

        Dependente dependente = dependenteService.findByIdOrThrowException(idDependente, ApiMessages.UNIDADE_ORGANIZACIONAL_NAO_ENCONTRADO);

        dependencia.preencher(dependente);

        return super.create(dependencia);
    }
}
