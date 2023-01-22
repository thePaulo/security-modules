package br.gov.sead.pagrn.domain.vinculos.funcao;

import br.gov.sead.pagrn.domain.concrets.Funcao;
import br.gov.sead.pagrn.domain.events.ProvimentoDeCargo;
import br.gov.sead.pagrn.domain.events.ProvimentoDeFuncao;
import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
import br.gov.sead.pagrn.domain.type.TipoVinculo;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public abstract class VinculoFuncao extends AbstractEntity {
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dataExercicio;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dataPosse;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dataNomeacao;

    @ManyToOne
    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    protected Funcao funcao;

    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    protected boolean opcaoVencimento;

    @OneToOne
    protected Vinculo vinculo;

    public static VinculoFuncao fabricarVinculo(Vinculo vinculo, ProvimentoDeFuncao provimentoDeFuncao){
        VinculoFuncao vinculoFuncao;
        switch (provimentoDeFuncao.getTipoVinculo()){
            case COMISSIONADO -> {
                vinculoFuncao = new Comissionado();
            }
            default -> {throw new IllegalArgumentException(ApiMessages.TIPO_VINCULO_INEXISTENTE);}
        }
        vinculoFuncao.setFuncao(provimentoDeFuncao.getFuncao());
        vinculoFuncao.setDataNomeacao(provimentoDeFuncao.getDataNomeacao());
        vinculoFuncao.setDataPosse(provimentoDeFuncao.getDataPosse());
        vinculoFuncao.setDataExercicio(provimentoDeFuncao.getDataExercicio());
        vinculoFuncao.setOpcaoVencimento(provimentoDeFuncao.isOpcaoVencimento());
        vinculoFuncao.setVinculo(vinculo);
        return vinculoFuncao;
    }

    public abstract Set<SituacaoVinculo> proximasSituacoesPossiveis(SituacaoVinculo situacao);

    public abstract Set<String> eventosPermitidos();

    public abstract boolean validar();
}
