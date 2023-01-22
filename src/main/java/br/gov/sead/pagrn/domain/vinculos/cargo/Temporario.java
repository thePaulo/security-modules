package br.gov.sead.pagrn.domain.vinculos.cargo;

import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;

import javax.persistence.Entity;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Temporario extends VinculoCargo {

    @Override
    public Set<SituacaoVinculo> proximasSituacoesPossiveis(SituacaoVinculo situacao) {
        if(situacao.equals(SituacaoVinculo.ATIVO)){
            return Set.of(SituacaoVinculo.ENCERRADO);
        }
        else{
            throw new IllegalStateException(ApiMessages.SITUACAO_NAO_PERTENCE_AO_TIPO_DE_VINCULO);
        }
    }

    @Override
    public Set<String> eventosPermitidos() {
        Set<String> eventosPermitidos = new LinkedHashSet<>();
        switch (this.vinculo.getSituacao()){
            case ATIVO -> {
                eventosPermitidos.add("encerramento");
                eventosPermitidos.add("ferias");
                eventosPermitidos.add("faltas");
            }
        }
        return eventosPermitidos;
    }

    @Override
    public boolean validar() {
        this.vinculo.validarDatas(this.dataNomeacao, this.dataPosse, this.dataExercicio);
        return true;
    }

}
