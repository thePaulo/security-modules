package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity(name = "cbos")
@SQLDelete(sql = "UPDATE cbos SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cbo extends AbstractEntity {

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String codigo;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String denominacao;
}
