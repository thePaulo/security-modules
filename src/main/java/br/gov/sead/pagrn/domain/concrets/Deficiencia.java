package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.TipoDeficiencia;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;

@Entity(name = "deficiencias")
@SQLDelete(sql = "UPDATE deficiencias SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Deficiencia extends AbstractEntity{

    private TipoDeficiencia tipoDeficiencia;

    private String denominacao;
}
