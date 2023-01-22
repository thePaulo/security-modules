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
public class EnderecoDtoRequest {
    private String logradouro;

    private String numero;

    private String complemento;

    private String cep;

    private TipoLogradouro tipoLogradouro;

    private String bairro;

    private String cidade;

    private UnidadeFederativa unidadeFederativa;

    private Pais pais;

  
}
