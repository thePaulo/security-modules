package br.gov.sead.pagrn.domain.events;

import br.gov.sead.pagrn.domain.type.TipoAposentadoria;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "aposentadorias")
@SQLDelete(sql = "UPDATE aposentadorias SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Aposentadoria extends Evento implements Serializable {

    private TipoAposentadoria tipoAposentadoria;

    private LocalDate dataAposentadoria;

    private String processoAdministrativo;

    public void preencher() {
        this.setDataRegistro(LocalDate.now());
    }



    /**
     * metodo reponsavel por comparar objetos de Aposentadoria
     *
     * @param o objeto qualquer
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Aposentadoria that = (Aposentadoria) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}