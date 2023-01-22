package br.gov.sead.pagrn.service;


import br.gov.sead.pagrn.domain.concrets.Endereco;
import br.gov.sead.pagrn.domain.concrets.PessoaJuridica;
import br.gov.sead.pagrn.domain.concrets.Telefone;
import br.gov.sead.pagrn.repository.PessoaJuridicaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PessoaJuridicaService extends AbstractService<PessoaJuridica, PessoaJuridicaRepository> {

    /**
     * Construtor da classe pessoa jurídica
     * @param repository
     * */
    public PessoaJuridicaService(PessoaJuridicaRepository repository){
        super(repository);
    }

    public PessoaJuridica insert(PessoaJuridica pessoaJuridica/*, Endereco endereco, Set<Telefone> telefones*/) {
        //pessoaJuridica.preencher(endereco, telefones);
        return super.create(pessoaJuridica);
    }

    public PessoaJuridica update(Long id, PessoaJuridica pessoaJuridica, Endereco endereco, Set<Telefone> telefones) {
        pessoaJuridica.preencher(endereco, telefones);
        return super.update(id, pessoaJuridica);
    }
}