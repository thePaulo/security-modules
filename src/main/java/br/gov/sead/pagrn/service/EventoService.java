package br.gov.sead.pagrn.service;

import br.gov.sead.pagrn.domain.events.Evento;
import br.gov.sead.pagrn.repository.EventoRepository;
import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class EventoService extends AbstractService<Evento, EventoRepository> {
    public EventoService(EventoRepository repository){ super(repository);}
}
