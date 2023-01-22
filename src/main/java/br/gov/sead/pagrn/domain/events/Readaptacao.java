package br.gov.sead.pagrn.domain.events;

import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "readaptacoes")
@SQLDelete(sql = "UPDATE readaptacoes SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Readaptacao extends Evento implements Serializable {

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private LocalDate dataReadaptacao;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String motivo;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String dadosLaudo;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String dadosPublicacao;


    /**
     * metodo reponsavel por comparar objetos de Readaptação
     * @param o objeto qualquer
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Readaptacao that = (Readaptacao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}