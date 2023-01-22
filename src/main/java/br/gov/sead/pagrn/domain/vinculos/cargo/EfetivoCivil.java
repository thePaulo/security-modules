package br.gov.sead.pagrn.domain.vinculos.cargo;

import br.gov.sead.pagrn.domain.type.SituacaoVinculo;

import javax.persistence.Entity;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class EfetivoCivil extends VinculoCargo {

    @Override
    public Set<SituacaoVinculo> proximasSituacoesPossiveis(SituacaoVinculo situacao) {
        return proximaSituacaoEfetivos(situacao);
    }

    @Override
    public Set<String> eventosPermitidos() {
        Set<String> eventosPermitidos = new LinkedHashSet<>();
        switch (this.vinculo.getSituacao()){
            case ATIVO -> {
                eventosPermitidos.add("afastamento");
                eventosPermitidos.add("aposentadoria");
                eventosPermitidos.add("disponibilidade");
                eventosPermitidos.add("vacancia");
                eventosPermitidos.add("exoneracao");
                eventosPermitidos.add("ferias");
                eventosPermitidos.add("faltas");
            }
            case AFASTADO -> eventosPermitidos.add("retorno");
            case APOSENTADO -> eventosPermitidos.add("reversao");
            case SUSPENSO -> eventosPermitidos.add("reconducao");
            case EM_DISPOSICAO -> eventosPermitidos.add("aproveitamento");
        }
        return eventosPermitidos;
    }

    @Override
    public boolean validar() {
        this.vinculo.validarDatas(this.dataNomeacao, this.dataPosse, this.dataExercicio);
        return true;
    }
}
