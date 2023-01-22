package br.gov.sead.pagrn.repository.generic;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenericRepository<E extends AbstractEntity> extends PagingAndSortingRepository<E, Long>, JpaSpecificationExecutor<E> {
}