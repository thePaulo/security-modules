package br.gov.sead.pagrn.dto.endereco;

import br.gov.sead.pagrn.domain.type.Pais;
import br.gov.sead.pagrn.domain.type.TipoLogradouro;
import br.gov.sead.pagrn.domain.type.UnidadeFederativa;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnderecoDtoResponse {

    private Long id;

    private String logradouro;

    private String numero;

    private String complemento;

    private String cep;

    private TipoLogradouro tipoLogradouro;

    private UnidadeFederativa unidadeFederativa;

    private String bairro;

    private String cidade;

    private Pais pais;


}
