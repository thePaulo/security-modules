package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.vinculos.funcao.VinculoFuncao;
import br.gov.sead.pagrn.repository.VinculoFuncaoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class VinculoFuncaoService extends AbstractService<VinculoFuncao, VinculoFuncaoRepository> {
    public VinculoFuncaoService(VinculoFuncaoRepository repository){
        super(repository);
    }
}
