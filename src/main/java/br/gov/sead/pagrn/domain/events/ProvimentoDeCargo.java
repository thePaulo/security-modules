package br.gov.sead.pagrn.domain.events;

import br.gov.sead.pagrn.domain.concrets.Especialidade;
import br.gov.sead.pagrn.domain.concrets.PessoaJuridica;
import br.gov.sead.pagrn.domain.concrets.RemuneracaoBasica;
import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "provimento_de_cargo")
@SQLDelete(sql = "UPDATE provimento_de_cargo SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProvimentoDeCargo extends Provimento{

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "remuneracao_basica_id")
    private RemuneracaoBasica remuneracaoBasica;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

    public void preencher(PessoaJuridica pessoaJuridicaContratante, UnidadeOrganizacional uoPagante,
                          RemuneracaoBasica remuneracaoBasica) {
        super.pessoaJuridicaContratante = pessoaJuridicaContratante;
        super.uoPagante = uoPagante;
        this.remuneracaoBasica = remuneracaoBasica;
        this.dataRegistro = LocalDate.now();
    }
}
