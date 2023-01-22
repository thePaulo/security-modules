package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.*;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity(name = "pessoas_fisicas")
@SQLDelete(sql = "UPDATE pessoas_fisicas SET deletedAt = CURRENT_DATE WHERE id=?")
//@Where(clause = "deletedAt is null")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PessoaFisica extends AbstractEntity implements Serializable {

    @NotBlank(message = ApiMessages.ERRO_NOME)
    @Size(min = 3, message = ApiMessages.ERRO_NOME)
    private String nome;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @CPF(message = ApiMessages.ERRO_CPF)
    @Column(unique = true)
    private String cpf;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String nomePai;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String nomeMae;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String naturalidadeCidade;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String nacionalidadePais;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private EstadoVital estadoVital;

    @Email(message = ApiMessages.ERRO_EMAIL)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "telefone_id")
    private Set<Telefone> telefones;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @NotNull
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "deficiencias_id")
    private Set<Deficiencia> deficiencias;

    @OneToOne(cascade = CascadeType.ALL)
    private ContaBanco contaBanco;

    /**
     * Método reponsável por comparar objetos do tipo pessoa física
     * @param o objeto qualquer
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PessoaFisica that = (PessoaFisica) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void falecer() {
        this.estadoVital = EstadoVital.MORTO;
    }

    public void preencher() {
        this.setNome(this.getNome().toUpperCase());
        this.setNomePai(this.getNomePai().toUpperCase());
        this.setNomeMae(this.getNomeMae().toUpperCase());
    }
}
