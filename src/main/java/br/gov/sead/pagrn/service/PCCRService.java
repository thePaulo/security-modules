package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.service.generic.AbstractService;
import br.gov.sead.pagrn.domain.concrets.PCCR;
import br.gov.sead.pagrn.repository.PCCRRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PCCRService extends AbstractService<PCCR, PCCRRepository> {

    private UnidadeOrganizacionalService unidadeOrganizacionalService;

    public PCCRService(PCCRRepository repository, UnidadeOrganizacionalService unidadeOrganizacionalService)  {
        super(repository);
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
    }

    public PCCR insert(PCCR pccr, List<Long> idsUO) {

        List<UnidadeOrganizacional> unidadesOrganizacionais = new ArrayList<>();
        for(Long id: idsUO) {
            UnidadeOrganizacional unidadeOrganizacional = unidadeOrganizacionalService.findByIdOrThrowException(id, ApiMessages.UNIDADE_ORGANIZACIONAL_NAO_ENCONTRADO);
            unidadesOrganizacionais.add(unidadeOrganizacional);
        }

        pccr.setUnidadesOrganizacionais(unidadesOrganizacionais);
        return super.create(pccr);
    }

    public PCCR update(Long idPccr, PCCR pccr, List<Long> idsUO) {

        List<UnidadeOrganizacional> unidadesOrganizacionais = new ArrayList<>();
        for(Long id: idsUO) {
            UnidadeOrganizacional unidadeOrganizacional = unidadeOrganizacionalService.findByIdOrThrowException(id, ApiMessages.UNIDADE_ORGANIZACIONAL_NAO_ENCONTRADO);
            unidadesOrganizacionais.add(unidadeOrganizacional);
        }

        pccr.setUnidadesOrganizacionais(unidadesOrganizacionais);
        return super.update(idPccr, pccr);
    }
}