package br.gov.sead.pagrn.domain.vinculos;

import br.gov.sead.pagrn.domain.concrets.PessoaJuridica;
import br.gov.sead.pagrn.domain.concrets.Servidor;
import br.gov.sead.pagrn.domain.concrets.Setor;
import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.domain.events.Evento;
import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.RegimeJuridico;
import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
import br.gov.sead.pagrn.domain.type.TipoVinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;

@Entity(name = "vinculos")
@SQLDelete(sql = "UPDATE vinculos SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
public class Vinculo extends AbstractEntity implements Serializable {

    public Vinculo() {
        this.eventos = new LinkedHashSet<>();
    }

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    protected SituacaoVinculo situacao;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    protected RegimeJuridico regimeJuridico;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    protected TipoVinculo tipoVinculo;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "servidor_id")
    protected Servidor servidor;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @OneToOne
    protected Setor setor;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @OneToOne
    protected UnidadeOrganizacional uoPagante;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @ManyToOne
    @JoinColumn(name = "pessoa_juridica_contratante_id")
    protected PessoaJuridica pessoaJuridicaContratante;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @JoinColumn(name = "vinculo_id")
    protected Set<Evento> eventos;

    protected static void verificacaoCampo(Object o, String mensagem) {
        if (isNull(o)) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public void registrarEvento(Evento evento) {
        this.eventos.add(evento);
    }

    public void mudarSituacao(SituacaoVinculo novaSituacao) {
        Set<SituacaoVinculo> situacoesPossiveis = proximasSituacoesPossiveis(this.getSituacao());
        if(!situacoesPossiveis.contains(novaSituacao)){
            throw new IllegalStateException();
        }

        this.setSituacao(novaSituacao);
    }

    public Set<SituacaoVinculo> proximasSituacoesPossiveis(SituacaoVinculo situacao){
        throw new IllegalArgumentException(ApiMessages.METODO_NAO_IMPLEMENTADO);
    }

    public Set<String> eventosPermitidos(){
        throw new IllegalArgumentException(ApiMessages.METODO_NAO_IMPLEMENTADO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vinculo that = (Vinculo) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean validar(){
        throw new IllegalArgumentException(ApiMessages.METODO_NAO_IMPLEMENTADO);
    }

    public void validarDatas(LocalDate dataNomeacao, LocalDate dataPosse, LocalDate dataExercicio) {
        if (dataNomeacao.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(ApiMessages.DATA_NOMEACAO_INVALIDA);
        }
        if (dataPosse.isBefore(dataNomeacao) || dataPosse.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(ApiMessages.DATA_POSSE_INVALIDA);
        }
        if (dataExercicio.isBefore(dataNomeacao) || dataExercicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(ApiMessages.DATA_INICIO_EXERCICIO_INVALIDA);
        }
    }

    public void preencher(Setor setor, PessoaJuridica pessoaJuridica, UnidadeOrganizacional unidadeOrganizacional, Servidor servidor) {
        this.setor = setor;
        this.pessoaJuridicaContratante = pessoaJuridica;
        this.uoPagante = unidadeOrganizacional;
        this.servidor = servidor;
    }
}
