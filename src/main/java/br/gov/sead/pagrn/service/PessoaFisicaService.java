package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.PessoaFisica;
import br.gov.sead.pagrn.domain.concrets.Telefone;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.PessoaFisicaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PessoaFisicaService extends AbstractService<PessoaFisica, PessoaFisicaRepository> {

    /**
     * Construtor da classe pessoa física
     *
     * @param repository
     */
    public PessoaFisicaService(PessoaFisicaRepository repository) {
        super(repository);
    }

    public PessoaFisica insert(PessoaFisica pessoaFisica) {
        pessoaFisica.preencher();
        return super.create(pessoaFisica);
    }

    public PessoaFisica registrarFalecimento(Long idPessoaFisica) {
        PessoaFisica pessoaFisica = this.findByIdOrThrowException(idPessoaFisica, ApiMessages.PESSOA_FISICA_NAO_ENCONTRADA);
        pessoaFisica.falecer();
        return super.update(idPessoaFisica, pessoaFisica);
    }
}

