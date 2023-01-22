package br.gov.sead.pagrn.domain.concrets;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity(name = "regimes_previdenciarios")
@SQLDelete(sql = "UPDATE regimes_previdenciarios SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class RegimePrevidenciario extends AbstractEntity {
    
    @NotBlank
    private String denominacao;

    @NotBlank
    private String mneumonico;

    public void preencher() {
        this.setDenominacao((this.getDenominacao().toUpperCase()));
        this.setMneumonico(this.getMneumonico().toUpperCase());
    }

}