package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "jornadas_trabalho")
@SQLDelete(sql = "UPDATE jornadas_trabalho SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class JornadaTrabalho extends AbstractEntity {

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private int horas_jornada_semana;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private int plantoes_mes;
}
