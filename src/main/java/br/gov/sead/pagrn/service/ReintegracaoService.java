package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Reintegracao;
import br.gov.sead.pagrn.repository.ReintegracaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReintegracaoService extends AbstractService<Reintegracao, ReintegracaoRepository> {
    private final VinculoService vinculoService;

    /**
     * Construtor da classe
     *
     * @param repository
     * @param vinculoService
     */
    public ReintegracaoService(ReintegracaoRepository repository, VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Reintegracao reintegrar(Reintegracao reintegracao, Long idVinculo){
        reintegracao.preencher();
        Reintegracao reintegracaoSaved = super.create(reintegracao);
        vinculoService.registrarReintegracao(reintegracaoSaved, idVinculo);
        return reintegracaoSaved;
    }
}
