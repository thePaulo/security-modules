package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Cbo;
import br.gov.sead.pagrn.repository.CboRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class CboService extends AbstractService<Cbo, CboRepository> {

    public CboService(CboRepository repository){
        super(repository);
    }
}
