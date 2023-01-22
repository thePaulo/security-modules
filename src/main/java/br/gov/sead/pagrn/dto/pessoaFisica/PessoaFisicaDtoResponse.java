package br.gov.sead.pagrn.dto.pessoaFisica;

import br.gov.sead.pagrn.domain.type.EstadoCivil;
import br.gov.sead.pagrn.domain.type.EstadoVital;
import br.gov.sead.pagrn.domain.type.Genero;
import br.gov.sead.pagrn.domain.type.TipoSanguineo;
import br.gov.sead.pagrn.dto.contaBanco.ContaBancoDtoResponse;
import br.gov.sead.pagrn.dto.deficiencia.DeficienciaDtoResponse;
import br.gov.sead.pagrn.dto.endereco.EnderecoDtoResponse;
import br.gov.sead.pagrn.dto.telefone.TelefoneDtoResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
public class PessoaFisicaDtoResponse{

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private Genero genero;

    private LocalDate dataNascimento;

    private TipoSanguineo tipoSanguineo;

    private String nomeMae;

    private String nomePai;

    private EstadoCivil estadoCivil;

    private String nacionalidadePais;

    private String naturalidadeCidade;

    private Set<DeficienciaDtoResponse> deficiencias;

    private Set<TelefoneDtoResponse> telefones;

    private EnderecoDtoResponse endereco;

    private ContaBancoDtoResponse contaBanco;

    private EstadoVital estadoVital;

    /**
     * Construtor de pessoaFisicaDtoResponse, transfere os dados enviando respostas do controller ao front
     *
     * @param pessoaFisica
     */

}
