package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Sancao;
import br.gov.sead.pagrn.repository.SancaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SancaoService extends AbstractService<Sancao, SancaoRepository> {

    private final VinculoService vinculoService;

    public SancaoService(SancaoRepository repository,VinculoService vinculoService) {
        super(repository);
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Sancao sancionar(Sancao sancao, Long idVinculo){
        sancao.preencher();
        Sancao sancaoSaved = super.create(sancao);

        vinculoService.colocarEmDisponibilidade(sancaoSaved, idVinculo);
        return sancaoSaved;
    }
}
