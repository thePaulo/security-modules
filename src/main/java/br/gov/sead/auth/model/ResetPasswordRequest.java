package br.gov.sead.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {

    String type;
    String value;
    boolean temporary;
}
