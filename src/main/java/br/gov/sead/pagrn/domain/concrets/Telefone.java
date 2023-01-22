package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "telefones")
@SQLDelete(sql = "UPDATE telefones SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Telefone extends AbstractEntity {

    private String celular;

    private String residencial;

    /**
     * Método reponsável por comparar objetos do tipo telefone
     * @param o objeto qualquer
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Telefone telefone = (Telefone) o;
        return getId() != null && Objects.equals(getId(), telefone.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
