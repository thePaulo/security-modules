package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Pensao;
import br.gov.sead.pagrn.domain.concrets.Pensionista;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.PensaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class PensaoService extends AbstractService<Pensao, PensaoRepository> {

    PensionistaService pensionistaService;
    /**
     * Construtor da classe
     *
     * @param repository
     */
    public PensaoService(PensaoRepository repository, PensionistaService pensionistaService) {
        super(repository);
        this.pensionistaService = pensionistaService;
    }

    public Pensao insert(Long idPensionista, Pensao pensao) {

        Pensionista pensionista = pensionistaService.findByIdOrThrowException(idPensionista, ApiMessages.PENSIONISTA_NAO_ENCONTRADO);

        pensao.preencher(pensionista);

        return super.create(pensao);

    }
}
