package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.service.generic.AbstractService;
import br.gov.sead.pagrn.domain.concrets.CarreiraCargo;
import br.gov.sead.pagrn.repository.CarreiraCargoRepository;
import org.springframework.stereotype.Service;

@Service
public class CarreiraCargoService extends AbstractService<CarreiraCargo, CarreiraCargoRepository> {

    public CarreiraCargoService(CarreiraCargoRepository repository) {
        super(repository);
    }
}