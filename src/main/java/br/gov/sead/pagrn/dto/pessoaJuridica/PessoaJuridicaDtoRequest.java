package br.gov.sead.pagrn.dto.pessoaJuridica;

import br.gov.sead.pagrn.domain.type.ClassificacaoTributaria;
import br.gov.sead.pagrn.dto.endereco.EnderecoDtoRequest;
import br.gov.sead.pagrn.dto.telefone.TelefoneDtoRequest;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class PessoaJuridicaDtoRequest {

    private String nomeFantasia;

    private String razaoSocial;

    private String cnpj;

    private ClassificacaoTributaria classificacaoTributaria;

    private EnderecoDtoRequest endereco;

}