package br.gov.sead.pagrn.dto.provimentoDeFuncao;

import br.gov.sead.pagrn.domain.type.RegimeJuridico;
import br.gov.sead.pagrn.domain.type.TipoVinculo;
import br.gov.sead.pagrn.dto.GenericEventoDtoRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ProvimentoDeFuncaoDtoRequest extends GenericEventoDtoRequest {

    //provimento de funcao
    private Long pessoaJuridicaContratanteId;

    private Long uoPaganteId;

    private RegimeJuridico regimeJuridico;

    private TipoVinculo tipoVinculo;

    private Long funcaoId;

    private LocalDate dataNomeacao;

    private LocalDate dataPosse;

    private LocalDate dataExercicio;

    private String processoAdministrativo;

    private boolean opcaoVencimento;

    //evento

    private LocalDate dataVigencia;

    private String descricao;

}
