package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "funcoes")
@SQLDelete(sql = "UPDATE funcoes SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Funcao extends AbstractEntity implements Serializable {

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String denominacao;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private Integer  vagas;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataCriacao;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataExtincao;

    @ManyToOne
    @JoinColumn(name = "unidade_organizacional_id")
    @NotNull(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private UnidadeOrganizacional unidadeOrganizacional;

    @ManyToOne
    @NotNull(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private Cbo cbo;

    @ManyToOne
    @NotNull(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private TipoGratificacao tipoGratificacao;
}

