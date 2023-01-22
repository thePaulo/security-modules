package br.gov.sead.pagrn.dto.provimentoDeCargo;

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
public class ProvimentoDeCargoDtoRequest extends GenericEventoDtoRequest {

    //provimento de cargo
    private Long pessoaJuridicaContratanteId;

    private Long uoPaganteId;

    private RegimeJuridico regimeJuridico;

    private TipoVinculo tipoVinculo;

    private Long remuneracaoBasicaId;

    private Long especialidadeId;

    private LocalDate dataNomeacao;

    private LocalDate dataPosse;

    private LocalDate dataExercicio;

    private String processoAdministrativo;

    //evento

    private LocalDate dataVigencia;

    private String descricao;

}
