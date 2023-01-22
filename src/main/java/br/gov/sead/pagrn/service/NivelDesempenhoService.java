package br.gov.sead.pagrn.service;

import org.springframework.stereotype.Service;

import br.gov.sead.pagrn.domain.concrets.NivelDesempenho;
import br.gov.sead.pagrn.repository.NivelDesempenhoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;

@Service
public class NivelDesempenhoService extends AbstractService<NivelDesempenho, NivelDesempenhoRepository> {
    
    public NivelDesempenhoService(NivelDesempenhoRepository repository){
        super(repository);
    }

}