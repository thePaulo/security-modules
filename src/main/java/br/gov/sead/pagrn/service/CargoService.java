package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Cargo;
import br.gov.sead.pagrn.domain.concrets.Cbo;
import br.gov.sead.pagrn.domain.concrets.GrupoOcupacional;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.CargoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CargoService extends AbstractService<Cargo, CargoRepository> {

    private CboService cboService;
    private GrupoOcupacionalService grupoOcupacionalService;


    public CargoService(CargoRepository repository, CboService cboService, GrupoOcupacionalService grupoOcupacionalService) { 
        super(repository);
        this.cboService = cboService;
        this.grupoOcupacionalService = grupoOcupacionalService;
    }

    @Transactional
    public Cargo criarCargo(Cargo cargo, Long cboId, Long grupoId){

        Cbo cbo = cboService.findByIdOrThrowException(cboId, ApiMessages.CBO_NAO_ENCONTRADO);
        GrupoOcupacional grupo = grupoOcupacionalService.findByIdOrThrowException(grupoId, ApiMessages.GRUPO_OCUPACIONAL_NAO_ENCONTRADO);

        cargo.setCbo(cbo);
        cargo.setGrupoOcupacional(grupo);

        return super.create(cargo);
    }

    @Transactional
    public Cargo atualizarCargo(Long id, Cargo cargo, Long cboId, Long grupoId){

        Cbo cbo = cboService.findByIdOrThrowException(cboId, ApiMessages.CBO_NAO_ENCONTRADO);
        GrupoOcupacional grupo = grupoOcupacionalService.findByIdOrThrowException(grupoId, ApiMessages.GRUPO_OCUPACIONAL_NAO_ENCONTRADO);

        cargo.setCbo(cbo);
        cargo.setGrupoOcupacional(grupo);

        return super.update(id ,cargo);
    }

}
