package br.gov.sead.pagrn.dto.pessoaJuridica;

import br.gov.sead.pagrn.domain.type.ClassificacaoTributaria;
import br.gov.sead.pagrn.dto.endereco.EnderecoDtoResponse;
import br.gov.sead.pagrn.dto.telefone.TelefoneDtoResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PessoaJuridicaDtoResponse {

    private Long id;

    private String nomeFantasia;

    private String razaoSocial;

    private String cnpj;

    private ClassificacaoTributaria classificacaoTributaria;

    private EnderecoDtoResponse endereco;

}