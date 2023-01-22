package br.gov.sead.auth.externalModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagrnKeycloakUser  {
    @Id
    private String id;
    private long created_timestamp;
    private String username;
    private String first_name;
    private String last_name;
    private String realm_id;
    private boolean enabled;
    private String federation_link;
    private boolean email_verified;
    private String email;
    private String email_constraint;
    //private String [] disableCredentialTypes;
    //private String [] requiredActions;
    private int not_before ;
    //other access entity values missing...
}
