package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.service.generic.AbstractService;
import br.gov.sead.pagrn.domain.concrets.CargoEspecialidade;
import br.gov.sead.pagrn.repository.CargoEspecialidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoEspecialidadeService extends AbstractService<CargoEspecialidade, CargoEspecialidadeRepository> {

    public CargoEspecialidadeService(CargoEspecialidadeRepository repository) {
        super(repository);
    }
}