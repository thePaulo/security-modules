package br.gov.sead.pagrn.repository.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public class SearchBuilder<E, R extends PagingAndSortingRepository<E, ?> & JpaSpecificationExecutor<E>> {

    private final R repository;

    private Specification<E> filters;

    private Sort sort = Sort.unsorted();

    public static <E, R extends PagingAndSortingRepository<E, ?> & JpaSpecificationExecutor<E>> SearchBuilder<E, R> usingRepository(
            R repository) {
        return new SearchBuilder<>(repository);
    }

    private SearchBuilder(R repository) {
        this.repository = repository;
    }

    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(filters, pageable);
    }

    public SearchBuilder<E, R> filterBy(List<String> listFilters) {
        Optional<Specification<E>> opFilters = EntitySpecificationBuilder.parse(listFilters);
        if (opFilters.isPresent()) {
            if (filters == null) {
                filters = Specification.where(opFilters.get());
            } else {
                filters = filters.and(opFilters.get());
            }
        }
        return this;
    }

    public SearchBuilder<E, R> sortBy(String orderBy, String orderDir) {
        if (StringUtils.isNotEmpty(orderBy)) {
            sort = Sort.by(Direction.fromOptionalString(orderDir).orElse(Direction.ASC), orderBy);
        }
        return this;
    }
}