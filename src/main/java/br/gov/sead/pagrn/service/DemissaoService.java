package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Demissao;
import br.gov.sead.pagrn.repository.DemissaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemissaoService extends AbstractService<Demissao, DemissaoRepository> {

    private final VinculoService vinculoService;

    public DemissaoService(DemissaoRepository repository, VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Demissao demitir(Demissao demissao, Long idVinculo) {
        demissao.preencher();
        Demissao demissaoSaved = super.create(demissao);
        vinculoService.registrarDemissao(demissaoSaved, idVinculo);
        return demissaoSaved;
    }

}
