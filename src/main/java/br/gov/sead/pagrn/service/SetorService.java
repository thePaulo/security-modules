package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.Endereco;
import br.gov.sead.pagrn.domain.concrets.Setor;
import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.SetorRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

@Service
public class SetorService extends AbstractService<Setor, SetorRepository> {

    private final UnidadeOrganizacionalService unidadeOrganizacionalService;

    public SetorService(SetorRepository repository, UnidadeOrganizacionalService unidadeOrganizacionalService) {
        super(repository);
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
    }

    @Transactional
    public Setor insert(Setor setor, Long idSetorSuperior, Long idUnidadeOrganizacional, Endereco endereco) {
        Setor setorSuperior = null;
        if (nonNull(idSetorSuperior)) {
            setorSuperior = super.findByIdOrThrowException(idSetorSuperior, ApiMessages.SETOR_NAO_ENCONTRADO);
        }

        UnidadeOrganizacional unidadeOrganizacional = unidadeOrganizacionalService
                .findByIdOrThrowException(idUnidadeOrganizacional, ApiMessages.UNIDADE_ORGANIZACIONAL_NAO_ENCONTRADO);

        setor.preencher(endereco, setorSuperior, unidadeOrganizacional);
        return super.create(setor);
    }



    public Setor update(Long id,Setor setor) {
        return super.update(id, setor);
    }
}
