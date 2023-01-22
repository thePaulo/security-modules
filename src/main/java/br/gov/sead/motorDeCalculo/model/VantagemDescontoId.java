package br.gov.sead.motorDeCalculo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VantagemDescontoId implements Serializable {

    private String codRubrica;

    private Snapshot snapshot;
    
}