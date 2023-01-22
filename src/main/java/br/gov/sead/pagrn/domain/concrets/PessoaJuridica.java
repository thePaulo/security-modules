package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.ClassificacaoTributaria;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.Objects;

@Entity(name = "pessoas_juridicas")
@SQLDelete(sql = "UPDATE pessoas_juridicas  SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PessoaJuridica extends AbstractEntity implements Serializable {

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String nomeFantasia;

    @ManyToOne(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private Endereco endereco;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String razaoSocial;

    @CNPJ(message = ApiMessages.ERRO_CNPJ)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private ClassificacaoTributaria classificacaoTributaria;

    /**
     * Método reponsável por comparar objetos do tipo pessoa jurídica
     * @param o objeto qualquer
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PessoaJuridica that = (PessoaJuridica) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void preencher(Endereco endereco, Set<Telefone> telefones) {
        this.setEndereco(endereco);
    }
}
