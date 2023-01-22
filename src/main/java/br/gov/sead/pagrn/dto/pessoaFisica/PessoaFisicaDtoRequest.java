package br.gov.sead.pagrn.dto.pessoaFisica;

import br.gov.sead.pagrn.domain.type.EstadoCivil;
import br.gov.sead.pagrn.domain.type.EstadoVital;
import br.gov.sead.pagrn.domain.type.Genero;
import br.gov.sead.pagrn.domain.type.TipoSanguineo;
import br.gov.sead.pagrn.dto.contaBanco.ContaBancoDtoRequest;
import br.gov.sead.pagrn.dto.deficiencia.DeficienciaDtoRequest;
import br.gov.sead.pagrn.dto.endereco.EnderecoDtoRequest;
import br.gov.sead.pagrn.dto.telefone.TelefoneDtoRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
public class PessoaFisicaDtoRequest {

    private String nome;

    private String email;

    private Genero genero;

    private String cpf;

    private LocalDate dataNascimento;

    private TipoSanguineo tipoSanguineo;

    private String nomeMae;

    private String nomePai;

    private EstadoCivil estadoCivil;

    private String nacionalidadePais;

    private String naturalidadeCidade;

    private Set<TelefoneDtoRequest> telefones;

    private EnderecoDtoRequest endereco;

    private Set<DeficienciaDtoRequest> deficiencias;

    private ContaBancoDtoRequest contaBanco;

    private EstadoVital estadoVital;

    /**
     * Método para converter os dados recebidos em um objeto do tipo pessoa física
     * */

}
