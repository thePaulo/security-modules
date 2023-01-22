package br.gov.sead.motorDeCalculo.service;

import br.gov.sead.motorDeCalculo.model.Contracheque;
import br.gov.sead.motorDeCalculo.repository.ContrachequeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContrachequeService {

    private ContrachequeRepository repository;

    public ContrachequeService(ContrachequeRepository repository) {
        this.repository = repository;
    }

    public List<Contracheque> findAll() {
        return repository.findAll();
    }

}
