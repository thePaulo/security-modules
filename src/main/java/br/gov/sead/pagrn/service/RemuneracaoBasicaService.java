package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Cargo;
import br.gov.sead.pagrn.domain.concrets.JornadaTrabalho;
import br.gov.sead.pagrn.domain.concrets.NivelDesempenho;
import br.gov.sead.pagrn.domain.concrets.RemuneracaoBasica;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.RemuneracaoBasicaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemuneracaoBasicaService extends AbstractService<RemuneracaoBasica, RemuneracaoBasicaRepository> {

    private CargoService cargoService;
    private JornadaTrabalhoService jornadaTrabalhoService;
    private NivelDesempenhoService nivelDesempenhoService;

    public RemuneracaoBasicaService(RemuneracaoBasicaRepository repository, CargoService cargoService, JornadaTrabalhoService jornadaTrabalhoService, NivelDesempenhoService nivelDesempenhoService) { 
        super(repository);
        this.cargoService = cargoService;
        this.jornadaTrabalhoService = jornadaTrabalhoService;
        this.nivelDesempenhoService = nivelDesempenhoService;
    }

    @Transactional
    public RemuneracaoBasica criarRemuneracao(RemuneracaoBasica remuneracaoBasica, Long cargoId, Long jornadaId, Long nivelDesempenhoId){
        
        Cargo cargo = cargoService.findByIdOrThrowException(cargoId, ApiMessages.CARGO_NAO_ENCONTRADO);
        JornadaTrabalho jornadaTrabalho = jornadaTrabalhoService.findByIdOrThrowException(jornadaId, ApiMessages.JORNADA_NAO_ENCONTRADA);
        NivelDesempenho nivelDesempenho = nivelDesempenhoService.findByIdOrThrowException(nivelDesempenhoId, ApiMessages.NIVEL_DESEMPENHO_NAO_ENCONTRADO);

        remuneracaoBasica.setCargo(cargo);
        remuneracaoBasica.setJornadaTrabalho(jornadaTrabalho);
        remuneracaoBasica.setNivelDesempenho(nivelDesempenho);

        return super.create(remuneracaoBasica);
    }

    @Transactional
    public RemuneracaoBasica atualizarRemuneracao(Long id,RemuneracaoBasica remuneracaoBasica, Long cargoId, Long jornadaId, Long nivelDesempenhoId){
        
        Cargo cargo = cargoService.findByIdOrThrowException(cargoId, ApiMessages.CARGO_NAO_ENCONTRADO);
        JornadaTrabalho jornadaTrabalho = jornadaTrabalhoService.findByIdOrThrowException(jornadaId, ApiMessages.JORNADA_NAO_ENCONTRADA);
        NivelDesempenho nivelDesempenho = nivelDesempenhoService.findByIdOrThrowException(nivelDesempenhoId, ApiMessages.NIVEL_DESEMPENHO_NAO_ENCONTRADO);

        remuneracaoBasica.setCargo(cargo);
        remuneracaoBasica.setJornadaTrabalho(jornadaTrabalho);
        remuneracaoBasica.setNivelDesempenho(nivelDesempenho);

        return super.update(id, remuneracaoBasica);
    }
}
