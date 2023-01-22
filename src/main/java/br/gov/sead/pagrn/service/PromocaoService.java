package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Promocao;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.PromocaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PromocaoService extends AbstractService<Promocao, PromocaoRepository> {

    private final VinculoService vinculoService;

    public PromocaoService(PromocaoRepository repository,VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Promocao promover(Promocao promocao, Long idNivelCargo, Long idVinculo){

        promocao.preencher();
        Promocao promocaoSaved = super.create(promocao);

        vinculoService.progredirCargo(promocaoSaved, idVinculo);
        return promocaoSaved;
    }

}
