package br.gov.sead.pagrn.domain.concrets;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "carreiras_cargos")
@SQLDelete(sql = "UPDATE carreiras_cargos SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class CarreiraCargo extends AbstractEntity{
    
    @ManyToOne
    @JoinColumn(name = "carreira_id")
    private Carreira carreira;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    private String grau;

    public void preencher(Carreira carreira, Cargo cargo) {
        this.setGrau((this.getGrau().toUpperCase()));
        this.setCarreira(carreira);
        this.setCargo(cargo);
    }
}