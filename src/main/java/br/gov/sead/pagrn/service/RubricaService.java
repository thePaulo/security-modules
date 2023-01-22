package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Rubrica;
import br.gov.sead.pagrn.repository.RubricaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class RubricaService extends AbstractService<Rubrica, RubricaRepository> {

    public RubricaService(RubricaRepository repository){
        super(repository);
    }
}
