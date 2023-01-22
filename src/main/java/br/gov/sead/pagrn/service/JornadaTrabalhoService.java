package br.gov.sead.pagrn.service;

import org.springframework.stereotype.Service;

import br.gov.sead.pagrn.domain.concrets.JornadaTrabalho;
import br.gov.sead.pagrn.repository.JornadaTrabalhoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;

@Service
public class JornadaTrabalhoService extends AbstractService<JornadaTrabalho, JornadaTrabalhoRepository> {
    
    public JornadaTrabalhoService(JornadaTrabalhoRepository repository){
        super(repository);
    }

}