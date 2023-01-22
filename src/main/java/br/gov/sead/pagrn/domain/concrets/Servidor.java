package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.Escolaridade;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "servidores")
@SQLDelete(sql = "UPDATE servidores SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Servidor extends AbstractEntity {

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @OneToOne
    @JoinColumn(name = "pessoa_fisica_id")
    private PessoaFisica pessoaFisica;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    @Column(unique = true)
    private String matricula;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    @Column(unique = true)
    private String pispasep;

    private String nomeSocial;

    public void preencher(PessoaFisica pessoaFisica) {
        this.setPessoaFisica(pessoaFisica);
        this.setNomeSocial(this.getNomeSocial().toUpperCase());
    }
}
