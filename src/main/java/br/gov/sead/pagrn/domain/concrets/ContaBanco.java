package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.TipoConta;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "dados_bancarios")
@SQLDelete(sql = "UPDATE dados_bancarios SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ContaBanco extends AbstractEntity implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private AgenciaBanco agenciaBanco;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String numeroConta;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String numeroContaDV;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;
}
