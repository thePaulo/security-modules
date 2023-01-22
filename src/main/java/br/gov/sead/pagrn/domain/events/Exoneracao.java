package br.gov.sead.pagrn.domain.events;

import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "exoneracoes")
@SQLDelete(sql = "UPDATE exoneracoes SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Exoneracao extends Evento implements Serializable {

    private String processoAdministrativo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Designacao that = (Designacao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

//    public void atualizarVinculo(Vinculo vinculo) {
//        vinculo.setFuncao(null);
//    }

    public void preencher() {
        this.setDataRegistro(LocalDate.now());
    }
}
