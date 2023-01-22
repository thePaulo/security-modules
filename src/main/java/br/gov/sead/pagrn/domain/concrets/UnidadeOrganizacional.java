package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "unidades_organizacionais")
@SQLDelete(sql = "UPDATE unidades_organizacionais SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@RequiredArgsConstructor
public class UnidadeOrganizacional extends AbstractEntity implements Serializable {

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String sigla;

    private String codigoLegado;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private LocalDate dataInicioOperacao;

    private LocalDate dataExtincao;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String codIbgeCnae;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @OneToOne
    private PessoaJuridica pessoaJuridica;

    public void preencher(PessoaJuridica pessoaJuridica) {
        this.setPessoaJuridica(pessoaJuridica);
    }
}
