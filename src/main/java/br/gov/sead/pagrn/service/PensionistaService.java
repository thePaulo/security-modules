package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.*;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.PensionistaRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class PensionistaService extends AbstractService<Pensionista, PensionistaRepository> {

    private VinculoService vinculoService;
    private PessoaFisicaService pessoaFisicaService;

    public PensionistaService(PensionistaRepository repository, VinculoService vinculoService, PessoaFisicaService pessoaFisicaService) {
        super(repository);
        this.vinculoService = vinculoService;
        this.pessoaFisicaService = pessoaFisicaService;
    }

    public Pensionista insert(Long idVinculo, Long idPessoaPensionista, Long idPessoaProcurador, Pensionista pensionista) {

        Vinculo vinculo = vinculoService.findByIdOrThrowException(idVinculo, ApiMessages.VINCULO_NAO_ENCONTRADO);

        PessoaFisica pessoaPensionista = pessoaFisicaService.findByIdOrThrowException(idPessoaPensionista, ApiMessages.UNIDADE_ORGANIZACIONAL_NAO_ENCONTRADO);

        if (nonNull(idPessoaProcurador)){
            PessoaFisica pessoaProcurador = pessoaFisicaService.findByIdOrThrowException(idPessoaProcurador, ApiMessages.UNIDADE_ORGANIZACIONAL_NAO_ENCONTRADO);
            pensionista.setPessoaProcurador(pessoaProcurador);
        }

        pensionista.preencher(vinculo, pessoaPensionista);

        return super.create(pensionista);
    }

    public Pensionista update(Pensionista pensionista, Long id) {
        Pensionista pensionistaSaved = this.findByIdOrThrowException(id, ApiMessages.PENSIONISTA_NAO_ENCONTRADO);

        pensionista.preencher(pensionistaSaved.getVinculo(), pensionistaSaved.getPessoaPensionista());

        return super.update(id, pensionista);
    }
}
