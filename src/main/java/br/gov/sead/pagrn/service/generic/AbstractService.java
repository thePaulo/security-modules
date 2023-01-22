package br.gov.sead.pagrn.service.generic;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.repository.filter.SearchBuilder;
import br.gov.sead.pagrn.repository.generic.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;
/**
 * Classe generica do service
 * */
public abstract class AbstractService<E extends AbstractEntity, R extends GenericRepository<E>> implements IGenericService<E>  {
    protected final R repository;
    /**
     * Construtor da classe
     * @param repository
     * */
    public AbstractService(R repository) {
        this.repository = repository;
    }

    /**
     * metodo que lista todos os elementos dado uma query especifica
     * @return List
     * */
    @Override
    public Page<E> find(String query, Pageable pageable) {
        return SearchBuilder
                .usingRepository(repository)
                .filterBy(Collections.singletonList(query))
                .findAll(pageable);
    }

    /**
     * metodo que recebe, cria e salva uma nova entidade
     * @param entity
     * */
    @Override
    public E create(E entity) {
        return  repository.save(entity);
    }


    /**
     * metodo que atualiza a entidade de um repositorio de acordo com o id passado
     * @param id
     * @param entity
     * */
    @Override
    public E update(Long id, E entity) {
        Optional<E> e = repository
                .findById(id)
                .map(record -> {
                    repository.save(entity);
                    //repository.saveAndFlush(entity);
                    return record;
                });

        if(e.isPresent()) {
            return e.get();
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    /**
     * metodo para deletar uma entidade de um repositorio de acordo com o id passado
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        Optional<E> e = repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return record;
                });

        if(e.isEmpty()) {
            throw new EntityNotFoundException();
        }
    }

    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    public E findByIdOrThrowException(Long id, String message){
        Optional<E> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException(message);
        }
        return entity.get();
    }
}