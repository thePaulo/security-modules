package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.PessoaJuridica;
import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.UnidadeOrganizacionalRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnidadeOrganizacionalService extends AbstractService<UnidadeOrganizacional, UnidadeOrganizacionalRepository> {

    public PessoaJuridicaService pessoaJuridicaService;

    public UnidadeOrganizacionalService(UnidadeOrganizacionalRepository repository, PessoaJuridicaService pessoaJuridicaService){
        super(repository);
        this.pessoaJuridicaService = pessoaJuridicaService;
    }


    @Transactional
    public UnidadeOrganizacional insert(UnidadeOrganizacional unidadeOrganizacional, Long idPessoaJuridica) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaService
                .findByIdOrThrowException(idPessoaJuridica, ApiMessages.PESSOA_JURIDICA_NAO_ENCONTRADA);

        unidadeOrganizacional.preencher(pessoaJuridica);
        return super.create(unidadeOrganizacional);
    }

    @Transactional
    public UnidadeOrganizacional update(Long id, UnidadeOrganizacional unidadeOrganizacional, Long idPessoaJuridica) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaService
                .findByIdOrThrowException(idPessoaJuridica, ApiMessages.PESSOA_JURIDICA_NAO_ENCONTRADA);

        unidadeOrganizacional.preencher(pessoaJuridica);
        return super.update(id, unidadeOrganizacional);
    }
}
