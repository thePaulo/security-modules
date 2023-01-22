package br.gov.sead.pagrn.domain.events;

import br.gov.sead.pagrn.domain.concrets.Funcao;
import br.gov.sead.pagrn.domain.concrets.PessoaJuridica;
import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity(name = "provimento_de_funcao")
@SQLDelete(sql = "UPDATE provimento_de_funcao SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProvimentoDeFuncao extends Provimento{

    @ManyToOne
    @JoinColumn(name = "funcao_id")
    private Funcao funcao;

    private boolean opcaoVencimento;

    public void preencher(PessoaJuridica pessoaJuridicaContratante, UnidadeOrganizacional uoPagante, Funcao funcao) {
        super.pessoaJuridicaContratante = pessoaJuridicaContratante;
        super.uoPagante = uoPagante;
        this.funcao = funcao;
        this.dataRegistro = LocalDate.now();
    }
}
