package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Dependente;
import br.gov.sead.pagrn.domain.concrets.PessoaFisica;
import br.gov.sead.pagrn.domain.concrets.Servidor;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.DependenteRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DependenteService extends AbstractService<Dependente, DependenteRepository> {

    private final PessoaFisicaService pessoaFisicaService;

    private final ServidorService servidorService;

    /**
     * Construtor da classe dependenteService
     *
     * @param repository
     */
    public DependenteService(DependenteRepository repository, PessoaFisicaService pessoaFisicaService,
                             ServidorService servidorService) {
        super(repository);
        this.pessoaFisicaService = pessoaFisicaService;
        this.servidorService = servidorService;
    }

    @Transactional
    public Dependente insert(Dependente dependente, Long idPessoaFisica, Long idServidor) {

        PessoaFisica pessoaFisica = pessoaFisicaService.findByIdOrThrowException(idPessoaFisica, ApiMessages.PESSOA_FISICA_NAO_ENCONTRADA);

        Servidor servidor = servidorService.findByIdOrThrowException(idServidor, ApiMessages.SERVIDOR_NAO_ENCONTRADO);

        dependente.preencher(pessoaFisica, servidor);

        if (pessoaFisica.equals(servidor.getPessoaFisica())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ApiMessages.NAO_PODE_INSERIR_DEPENDENTE);
        }

        return super.create(dependente);
    }


    @Transactional
    public Dependente update(Dependente dependente, Long id) {

        Dependente dependenteSaved = this.findByIdOrThrowException(id, ApiMessages.DEPENDENTE_NAO_ENCONTRADO);

        dependente.preencher(dependenteSaved.getPessoaFisica(), dependenteSaved.getServidor());

        return super.update(id, dependente);
    }

}
