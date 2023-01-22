package br.gov.sead.pagrn.domain.type;

import lombok.Getter;

@Getter
public enum RegimeJuridico {
    
    CELETISTA(0, "CLT", RegimePrevidenciario.INSS),
    ESTATUTARIO(1, "RJU", RegimePrevidenciario.IPE),
    MILITAR(2, "MLT", RegimePrevidenciario.IPE);

    private Integer codigo;
    private String descricao;
    private RegimePrevidenciario regimePrevidenciario;

    RegimeJuridico(Integer codigo, String descricao, RegimePrevidenciario regimePrevidenciario) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.regimePrevidenciario = regimePrevidenciario;
    }

    public static RegimeJuridico toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(RegimeJuridico x : RegimeJuridico.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Regime jurídico inválido");
    }
}
