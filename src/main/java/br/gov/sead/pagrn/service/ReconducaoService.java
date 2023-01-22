package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Reconducao;
import br.gov.sead.pagrn.repository.ReconducaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReconducaoService extends AbstractService<Reconducao, ReconducaoRepository> {
    private final VinculoService vinculoService;

    /**
     * Construtor da classe
     *
     * @param repository
     * @param vinculoService
     */
    public ReconducaoService(ReconducaoRepository repository, VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Reconducao reconduzir(Reconducao reconducao, Long idVinculo){
            reconducao.preencher();
            Reconducao reconducaoSaved = super.create(reconducao);
            vinculoService.registrarReconducao(reconducaoSaved, idVinculo);
            return reconducaoSaved;
    }
}
