package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Reversao;
import br.gov.sead.pagrn.repository.ReversaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReversaoService extends AbstractService<Reversao, ReversaoRepository> {
    private final VinculoService vinculoService;

    /**
     * Construtor da classe
     *
     * @param repository
     * @param vinculoService
     */
    public ReversaoService(ReversaoRepository repository, VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Reversao reverter(Reversao reversao, Long idVinculo){
            reversao.preencher();
            Reversao reversaoSaved = super.create(reversao);
            vinculoService.registrarReversao(reversaoSaved, idVinculo);
            return reversaoSaved;
    }
}
