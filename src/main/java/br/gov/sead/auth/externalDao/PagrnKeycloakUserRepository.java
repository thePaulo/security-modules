package br.gov.sead.auth.externalDao;

import br.gov.sead.auth.externalModel.PagrnKeycloakUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PagrnKeycloakUserRepository extends JpaRepository<PagrnKeycloakUser,Long> {

    @Query(value = "select * from user_entity",nativeQuery = true)
    List<PagrnKeycloakUser> getAll();

    @Query(value = "select * from user_entity e where e.username = ?1",nativeQuery = true)
    List<PagrnKeycloakUser> findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_entity SET first_name = ?1 where username = '84833783452' ",nativeQuery = true)
    void alterFirstName(String fname);
}
