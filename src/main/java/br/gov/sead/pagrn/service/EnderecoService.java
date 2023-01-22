package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Endereco;
import br.gov.sead.pagrn.repository.EnderecoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;

public class EnderecoService extends AbstractService<Endereco, EnderecoRepository> {

    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }
}
