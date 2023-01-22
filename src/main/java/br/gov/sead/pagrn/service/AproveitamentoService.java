package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.concrets.*;
import br.gov.sead.pagrn.domain.events.Aproveitamento;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import br.gov.sead.pagrn.repository.AproveitamentoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AproveitamentoService extends AbstractService<Aproveitamento, AproveitamentoRepository> {

    private final VinculoService vinculoService;
    private final ServidorService servidorService;
    private final UnidadeOrganizacionalService unidadeOrganizacionalService;
    private final SetorService setorService;
    private final FuncaoService funcaoService;

    public AproveitamentoService(AproveitamentoRepository repository, VinculoService vinculoService, ServidorService servidorService,
                                 UnidadeOrganizacionalService unidadeOrganizacionalService, SetorService setorService,
                                 FuncaoService funcaoService) {
        super(repository);
        this.vinculoService = vinculoService;
        this.servidorService = servidorService;
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
        this.setorService = setorService;
        this.funcaoService = funcaoService;
    }

    @Transactional
    public Aproveitamento aproveitar(Aproveitamento aproveitamento, Long idVinculo, Long idNivelCargo, Long idSetor) {

        Setor setor = setorService.findByIdOrThrowException(idSetor, ApiMessages.SERVIDOR_NAO_ENCONTRADO);

        aproveitamento.preencher(setor);
        Aproveitamento aproveitamentoSaved = super.create(aproveitamento);

        vinculoService.aproveitarServidorEmOutroCargo(aproveitamentoSaved, idVinculo);
        return aproveitamentoSaved;
    }

}
