package br.gov.sead.pagrn.dto.carreira;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarreiraDtoResponse {

    private Long id;

    private String denominacao;

    private Set<Long> carreiracargosIds;

    private Long pccrId;
}
