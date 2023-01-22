package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.Banco;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "agencias_bancos")
@SQLDelete(sql = "UPDATE agencias_bancos SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class AgenciaBanco  extends AbstractEntity implements Serializable {

    private String DV;

    private String denominacao;

    @Enumerated(EnumType.STRING)
    private Banco banco;

}
