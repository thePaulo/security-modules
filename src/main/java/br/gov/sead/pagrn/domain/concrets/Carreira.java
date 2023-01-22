package br.gov.sead.pagrn.domain.concrets;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "carreiras")
@SQLDelete(sql = "UPDATE carreiras SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class Carreira extends AbstractEntity {
    
    @NotBlank
    private String denominacao;

    @ManyToOne
    @JoinColumn(name = "PCCR_id")
    private PCCR pccr;

    @OneToMany(mappedBy = "carreira", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CarreiraCargo> carreiracargos;

    public PCCR getPccr() {
        return pccr;
    }

    public void setPccr(PCCR pccr) {
        this.pccr = pccr;
    }

    public void preencher(PCCR pccr, Set<CarreiraCargo> carreiracargos) {
        this.setDenominacao((this.getDenominacao().toUpperCase()));
        this.setPccr(pccr);
        this.setCarreiracargos(carreiracargos);
    }
}