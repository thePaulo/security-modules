package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Falecimento;
import br.gov.sead.pagrn.domain.concrets.PessoaFisica;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.repository.FalecimentoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FalecimentoService  extends AbstractService<Falecimento, FalecimentoRepository> {

    private final PessoaFisicaService pessoaFisicaService;
    private final VinculoService vinculoService;

    public FalecimentoService(FalecimentoRepository repository, PessoaFisicaService pessoaFisicaService,VinculoService vinculoService) {
        super(repository);
        this.pessoaFisicaService = pessoaFisicaService;
        this.vinculoService = vinculoService;
    }

    @Transactional
    public Falecimento registrarFalecimento(Falecimento falecimento, Long idPessoaFisica) {
        PessoaFisica pessoaFisica = pessoaFisicaService.registrarFalecimento(idPessoaFisica);

        falecimento.preencher();
        Falecimento falecimentoSaved = super.create(falecimento);

        Page<Vinculo> vinculos = vinculoService.findByCPFdoServidor(pessoaFisica.getCpf(), Pageable.unpaged());

        for(Vinculo vinculo : vinculos) {
            vinculoService.registrarFalecimento(falecimentoSaved, vinculo.getId());
        }
        return falecimentoSaved;
    }
}
