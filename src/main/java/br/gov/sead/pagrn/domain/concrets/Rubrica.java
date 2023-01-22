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

@Entity(name = "rubricas")
@SQLDelete(sql = "UPDATE rubricas SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class
Rubrica extends AbstractEntity {

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String denominacao;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String sigla;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String mnemonico;
}
