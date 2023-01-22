package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.Pais;
import br.gov.sead.pagrn.domain.type.TipoLogradouro;
import br.gov.sead.pagrn.domain.type.UnidadeFederativa;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "enderecos")
@SQLDelete(sql = "UPDATE enderecos SET deletedAt = CURRENT_DATE WHERE id=?")
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Endereco extends AbstractEntity  {
    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String logradouro;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String numero;

    private String complemento;

    private Double latitude;

    private Double longitude;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String cep;

    @Enumerated(EnumType.STRING)
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private TipoLogradouro tipoLogradouro;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String bairro;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String cidade;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private UnidadeFederativa unidadeFederativa;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Pais pais;

    /**
     * Método reponsável por comparar objetos do tipo endereço
     * @param o objeto qualquer4
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Endereco endereco = (Endereco) o;
        return getId() != null && Objects.equals(getId(), endereco.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
