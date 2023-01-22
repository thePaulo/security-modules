package br.gov.sead.pagrn.service.generic;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface generica do service
 * */
public interface IGenericService <E extends AbstractEntity> {
    Page<E> find(String query, Pageable pageable);
    E create(E entity);
    E update(Long id, E entity);
    void delete(Long id);
}
