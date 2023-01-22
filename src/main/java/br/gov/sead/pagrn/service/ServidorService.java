package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.PessoaFisica;
import br.gov.sead.pagrn.domain.concrets.Servidor;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.ServidorRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServidorService extends AbstractService<Servidor, ServidorRepository> {

    /**
     * Construtor da classe ServidorService
     * @param repository
     * */
    private final PessoaFisicaService pessoaFisicaService;

    public ServidorService(ServidorRepository repository, PessoaFisicaService pessoaFisicaService){
        super(repository);
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @Transactional
    public Servidor insert(Servidor servidor, Long idPessoaFisica) {
        PessoaFisica pessoaFisica = pessoaFisicaService.findByIdOrThrowException(idPessoaFisica,
                ApiMessages.PESSOA_FISICA_NAO_ENCONTRADA);

        servidor.preencher(pessoaFisica);
        return super.create(servidor);
    }

    public Page<Servidor> findByCPF(String cpf, Pageable pageable) {
        return repository.findByCpf(cpf, pageable);
    }

    public Page<Servidor> findByNome(String nome, Pageable pageable) {
        return repository.findByNome(nome, pageable);
    }

}
