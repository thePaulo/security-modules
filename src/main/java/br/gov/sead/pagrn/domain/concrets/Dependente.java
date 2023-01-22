package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.TipoDependencia;
import br.gov.sead.pagrn.domain.type.TipoParentesco;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "dependentes")
@SQLDelete(sql = "UPDATE dependentes SET deletedAt = CURRENT_DATE WHERE id=?")
@NoArgsConstructor
@Getter
@Setter
public class Dependente extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "pessoa_fisica_id")
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private PessoaFisica pessoaFisica;

    @ManyToOne
    @JoinColumn(name = "servidor_id")
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Servidor servidor;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    private TipoParentesco tipoParentesco;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate vigencia;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataExtincao;

    public void preencher(PessoaFisica pessoaFisica, Servidor servidor) {
        this.setServidor(servidor);
        this.setPessoaFisica(pessoaFisica);
    }
}
