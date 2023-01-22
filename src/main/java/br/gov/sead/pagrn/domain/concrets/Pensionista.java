package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.TipoParentesco;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity(name = "pensionistas")
@SQLDelete(sql = "UPDATE pensionistas  SET removed = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Pensionista extends AbstractEntity {

    @OneToOne
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Vinculo vinculo;

    @OneToOne
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private PessoaFisica pessoaPensionista;

    @OneToOne
    private PessoaFisica pessoaProcurador;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private TipoParentesco tipoParentesco;

    private String observacoes;

    public void preencher(Vinculo vinculo, PessoaFisica pessoaPensionista) {
        this.setPessoaPensionista(pessoaPensionista);
        this.setVinculo(vinculo);
    }

    public void preencher(PessoaFisica pessoaPensionista, PessoaFisica pessoaProcurador) {

    }
}
