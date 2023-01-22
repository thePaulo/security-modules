package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Exoneracao;
import br.gov.sead.pagrn.repository.ExoneracaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExoneracaoService extends AbstractService<Exoneracao, ExoneracaoRepository> {
    private final VinculoService vinculoService;

    public ExoneracaoService(ExoneracaoRepository repository, VinculoService vinculoService){
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Exoneracao exonerar(Exoneracao exoneracao, Long idVinculo) {
        exoneracao.preencher();
        Exoneracao exoneracaoSaved = super.create(exoneracao);

//        vinculoService.destituirFuncaoOuCargo(exoneracaoSaved, idVinculo);
        return exoneracaoSaved;
    }
}
