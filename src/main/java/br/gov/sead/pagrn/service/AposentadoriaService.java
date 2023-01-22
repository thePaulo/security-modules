package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Aposentadoria;
import br.gov.sead.pagrn.repository.AposentadoriaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AposentadoriaService extends AbstractService<Aposentadoria, AposentadoriaRepository> {

    private final VinculoService vinculoService;

    public AposentadoriaService(AposentadoriaRepository repository, VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Aposentadoria aposentar(Aposentadoria aposentadoria, Long idVinculo) {
        aposentadoria.preencher();
        Aposentadoria aposentadoriaSaved = super.create(aposentadoria);
        vinculoService.registrarAposentadoria(aposentadoriaSaved, idVinculo);
        return aposentadoriaSaved;
    }

}
