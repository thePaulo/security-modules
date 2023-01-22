package br.gov.sead.pagrn.domain.events;

import br.gov.sead.pagrn.domain.concrets.PessoaJuridica;
import br.gov.sead.pagrn.domain.concrets.RemuneracaoBasica;
import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.domain.type.RegimeJuridico;
import br.gov.sead.pagrn.domain.type.TipoVinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class Provimento extends Evento implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pessoa_juridica_contratante_id")
    @NotNull
    protected PessoaJuridica pessoaJuridicaContratante;

    @ManyToOne
    @JoinColumn(name = "uo_pagante_id")
    @NotNull
    protected UnidadeOrganizacional uoPagante;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    protected RegimeJuridico regimeJuridico;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @Enumerated(EnumType.STRING)
    protected TipoVinculo tipoVinculo;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dataNomeacao;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dataPosse;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dataExercicio;

    @NotBlank
    protected String processoAdministrativo;

    /**
     * metodo reponsavel por comparar objetos de Nomeação
     *
     * @param o objeto qualquer
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Provimento that = (Provimento) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}