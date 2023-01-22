package br.gov.sead.motorDeCalculo.service;

import br.gov.sead.motorDeCalculo.model.RubricaContracheque;
import br.gov.sead.motorDeCalculo.repository.RubricaContrachequeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubricaContrachequeService {

    private RubricaContrachequeRepository repository;

    public RubricaContrachequeService(RubricaContrachequeRepository repository){
        this.repository = repository;
    }

    public List<RubricaContracheque> findAll() {
        return repository.findAll();
    }
}
