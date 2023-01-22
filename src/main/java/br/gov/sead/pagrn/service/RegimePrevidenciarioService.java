package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.service.generic.AbstractService;
import br.gov.sead.pagrn.domain.concrets.RegimePrevidenciario;
import br.gov.sead.pagrn.repository.RegimePrevidenciarioRepository;
import org.springframework.stereotype.Service;

@Service
public class RegimePrevidenciarioService extends AbstractService<RegimePrevidenciario, RegimePrevidenciarioRepository> {

    public RegimePrevidenciarioService(RegimePrevidenciarioRepository repository) {
        super(repository);
    }

    public RegimePrevidenciario insert(RegimePrevidenciario regimePrevidenciario) {
        regimePrevidenciario.preencher();
        return super.create(regimePrevidenciario);
    }
}