package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Vacancia;
import br.gov.sead.pagrn.repository.VacanciaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VacanciaService extends AbstractService<Vacancia, VacanciaRepository> {

    private final VinculoService vinculoService;

    public VacanciaService(VacanciaRepository repository, VinculoService vinculoService){
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Vacancia suspender(Vacancia vacancia, Long idVinculo) {
        vacancia.preencher();
        Vacancia vacanciaSaved = super.create(vacancia);
        vinculoService.registrarVacancia(vacanciaSaved, idVinculo);
        return vacanciaSaved;
    }
}
