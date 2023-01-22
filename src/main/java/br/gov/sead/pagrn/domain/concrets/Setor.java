package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "setores")
@SQLDelete(sql = "UPDATE setores SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class Setor extends AbstractEntity {

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String sigla;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String denominacao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Setor setorSuperior;

    @NotNull(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Endereco endereco;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UnidadeOrganizacional unidadeOrganizacional;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Tag> tags;

    public void preencher(Endereco endereco, Setor setorSuperior, UnidadeOrganizacional unidadeOrganizacional) {
        this.setUnidadeOrganizacional(unidadeOrganizacional);
        this.setSetorSuperior(setorSuperior);
        this.setEndereco(endereco);
    }
}
