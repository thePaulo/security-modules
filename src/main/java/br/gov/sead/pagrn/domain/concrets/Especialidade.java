package br.gov.sead.pagrn.domain.concrets;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity(name = "especialidades")
@SQLDelete(sql = "UPDATE especialidades SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class Especialidade extends AbstractEntity {
    
    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String denominacao;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CargoEspecialidade> cargoespecialidades;
    
    public void preencher(Set<CargoEspecialidade> cargoespecialidades) {
        this.setDenominacao((this.getDenominacao().toUpperCase()));
        this.setCargoespecialidades(cargoespecialidades);
    }
}