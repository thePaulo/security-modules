package br.gov.sead.pagrn.domain.concrets;

import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "tipos_gratificacoes")
@SQLDelete(sql = "UPDATE tipos_gratificacoes SET deletedAt = CURRENT_DATE WHERE id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TipoGratificacao extends AbstractEntity {

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String denominacao;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private BigDecimal valorVencimento;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    private BigDecimal valorRepresentacao;

    @NotBlank(message = ApiMessages.BRANCO_NAO_PERMITIDO)
    private String mneumonico;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataCriacao;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataExtincao;

    @ManyToOne
    private Rubrica rubrica;
}
