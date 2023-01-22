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

@Entity(name = "suspensoes")
@SQLDelete(sql = "UPDATE suspensoes SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Suspensao extends Evento implements Serializable {

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private LocalDate inicio;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private LocalDate fim;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String motivo;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Integer numeroDias;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private boolean multa;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String dadosPublicacao;

    /**
     * metodo reponsavel por comparar objetos de Suspensao
     * @param o objeto qualquer
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Suspensao that = (Suspensao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
