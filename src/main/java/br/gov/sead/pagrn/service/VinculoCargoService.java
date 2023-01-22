package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.vinculos.cargo.VinculoCargo;
import br.gov.sead.pagrn.repository.VinculoCargoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class VinculoCargoService extends AbstractService<VinculoCargo, VinculoCargoRepository> {
    public VinculoCargoService(VinculoCargoRepository repository){
        super(repository);
    }
}
