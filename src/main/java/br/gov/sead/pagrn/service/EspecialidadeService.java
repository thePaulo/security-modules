package br.gov.sead.pagrn.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import br.gov.sead.pagrn.domain.concrets.Especialidade;
import br.gov.sead.pagrn.repository.EspecialidadeRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import br.gov.sead.pagrn.domain.concrets.CargoEspecialidade;

@Service
public class EspecialidadeService extends AbstractService<Especialidade, EspecialidadeRepository> {
    
    public EspecialidadeService(EspecialidadeRepository repository){
        super(repository);
    }

    public Especialidade insert(Especialidade especialidade) {
        Set<CargoEspecialidade> cargoespecialidades = new HashSet<CargoEspecialidade>();
        especialidade.preencher(cargoespecialidades);
        return super.create(especialidade);
    }
}