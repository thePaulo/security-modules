package br.gov.sead.pagrn.domain.vinculos.cargo;

import br.gov.sead.pagrn.domain.concrets.RemuneracaoBasica;
import br.gov.sead.pagrn.domain.events.ProvimentoDeCargo;
import br.gov.sead.pagrn.domain.generic.AbstractEntity;
import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
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
public abstract class VinculoCargo extends AbstractEntity {

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
//    @NotNull(message = ApiMessages.NULO_NAO_PERMITIDO)
    protected RemuneracaoBasica remuneracaoBasica;

    @OneToOne
    protected Vinculo vinculo;

    public abstract Set<SituacaoVinculo> proximasSituacoesPossiveis(SituacaoVinculo situacao);

    public abstract Set<String> eventosPermitidos();

    public abstract boolean validar();

    public static VinculoCargo fabricarVinculo(Vinculo vinculo, ProvimentoDeCargo provimentoDeCargo){
        VinculoCargo vinculoCargo;
        switch (provimentoDeCargo.getTipoVinculo()){
            case EFETIVO_CIVIL -> {
                vinculoCargo = new EfetivoCivil();
            }
            case EFETIVO_MILITAR -> {
                vinculoCargo = new EfetivoMilitar();
            }
            case TEMPORARIO -> {
                vinculoCargo = new Temporario();
            }
            default -> {throw new IllegalArgumentException(ApiMessages.TIPO_VINCULO_INEXISTENTE);}
        }
        vinculoCargo.setDataExercicio(provimentoDeCargo.getDataExercicio());
        vinculoCargo.setDataNomeacao(provimentoDeCargo.getDataNomeacao());
        vinculoCargo.setDataPosse(provimentoDeCargo.getDataPosse());
        vinculoCargo.setRemuneracaoBasica(provimentoDeCargo.getRemuneracaoBasica());
        vinculoCargo.setVinculo(vinculo);
        return vinculoCargo;
    }


    public static Set<SituacaoVinculo> proximaSituacaoEfetivos(SituacaoVinculo situacao) {
        if(situacao.equals(SituacaoVinculo.ATIVO)){
            return Set.of(SituacaoVinculo.APOSENTADO, SituacaoVinculo.AFASTADO, SituacaoVinculo.EXONERADO,
                    SituacaoVinculo.SUSPENSO, SituacaoVinculo.EM_DISPOSICAO);
        }else if(situacao.equals(SituacaoVinculo.APOSENTADO) ||
                situacao.equals(SituacaoVinculo.EM_DISPOSICAO) ||
                situacao.equals(SituacaoVinculo.AFASTADO)){
            return Set.of(SituacaoVinculo.ATIVO);
        }else if(situacao.equals(SituacaoVinculo.SUSPENSO)){
            return Set.of(SituacaoVinculo.ATIVO, SituacaoVinculo.EXONERADO);
        }
        else{
            throw new IllegalStateException(ApiMessages.SITUACAO_NAO_PERTENCE_AO_TIPO_DE_VINCULO);
        }
    }
}
