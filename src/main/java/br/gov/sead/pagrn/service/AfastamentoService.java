package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Afastamento;
import br.gov.sead.pagrn.repository.AfastamentoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AfastamentoService extends AbstractService<Afastamento, AfastamentoRepository> {

    private final VinculoService vinculoService;

    public AfastamentoService(AfastamentoRepository repository, VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Afastamento afastar(Afastamento afastamento, Long idVinculo) {
        afastamento.preencher();
        Afastamento afastamentoSaved = super.create(afastamento);
        vinculoService.registrarAfastamento(afastamentoSaved, idVinculo);
        return afastamentoSaved;
    }
}
