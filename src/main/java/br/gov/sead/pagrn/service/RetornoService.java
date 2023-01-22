package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Retorno;
import br.gov.sead.pagrn.repository.RetornoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RetornoService extends AbstractService<Retorno, RetornoRepository> {
    private final VinculoService vinculoService;

    /**
     * Construtor da classe
     *
     * @param repository
     * @param vinculoService
     */
    public RetornoService(RetornoRepository repository, VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Retorno retornarAoCargoOuFuncao(Retorno retorno, Long idVinculo){
        retorno.preencher();
        Retorno retornoSaved = super.create(retorno);
        vinculoService.registrarRetorno(retornoSaved, idVinculo);
        return retornoSaved;
    }
}
