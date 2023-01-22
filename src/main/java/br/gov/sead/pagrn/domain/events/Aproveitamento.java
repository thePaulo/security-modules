package br.gov.sead.pagrn.domain.events;

import br.gov.sead.pagrn.domain.concrets.*;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "aproveitamentos")
@SQLDelete(sql = "UPDATE aproveitamentos SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Aproveitamento extends Evento implements Serializable {

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicialExercicio;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPosse;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAproveitamento;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;


    @NotBlank
    private String processoAdministrativo;

    public void atualizarVinculo(Vinculo vinculo) {
        vinculo.setSetor(this.setor);
    }

    /**
     * metodo reponsavel por comparar objetos de Aproveitamento
     *
     * @param o objeto qualquer
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Aproveitamento that = (Aproveitamento) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void preencher(Setor setor) {
        this.setSetor(setor);
        this.setDataRegistro(LocalDate.now());
    }
}