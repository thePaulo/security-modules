package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Encerramento;
import br.gov.sead.pagrn.repository.EncerramentoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EncerramentoService extends AbstractService<Encerramento, EncerramentoRepository> {
    private final VinculoService vinculoService;


    public EncerramentoService(EncerramentoRepository repository, VinculoService vinculoService){
        super(repository);
        this.vinculoService = vinculoService;
    }

    /*
     * Método para deginar um servidor para uma função. Um servidor que já exerça uma função não
     * pode ser designado.
     */
    @Transactional
    public Encerramento encerrarContrato(Encerramento Encerramento, Long idVinculo){
        Encerramento.preencher();
        Encerramento encerramentoSaved = super.create(Encerramento);

        vinculoService.encerrarContratoTemporario(encerramentoSaved, idVinculo);
        return encerramentoSaved;
    }
}
