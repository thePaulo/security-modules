package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.PeriodoAquisitivoFerias;
import br.gov.sead.pagrn.repository.PeridoAquisitivoFeriasRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class PeriodoAquisitivoFeriasService extends AbstractService<PeriodoAquisitivoFerias, PeridoAquisitivoFeriasRepository> {
    public PeriodoAquisitivoFeriasService(PeridoAquisitivoFeriasRepository repository){
        super(repository);
    }
}
