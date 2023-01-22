package br.gov.sead.pagrn.domain.vinculos.funcao;

import br.gov.sead.pagrn.domain.type.SituacaoVinculo;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.errorhandling.ApiMessages;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Comissionado extends VinculoFuncao {
    @Override
    public Set<SituacaoVinculo> proximasSituacoesPossiveis(SituacaoVinculo situacao) {
        throw new IllegalStateException(ApiMessages.VALIDACAO_NAO_IMPLEMENTADA);
    }

    @Override
    public Set<String> eventosPermitidos() {
        throw new IllegalStateException(ApiMessages.VALIDACAO_NAO_IMPLEMENTADA);
    }

    @Override
    public boolean validar() {
        this.vinculo.validarDatas(this.dataNomeacao, this.dataPosse, this.dataExercicio);
        return true;
    }
}
