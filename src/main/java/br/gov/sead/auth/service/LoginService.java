package br.gov.sead.auth.service;

import br.gov.sead.auth.externalModel.PagrnKeycloakUser;
import br.gov.sead.auth.externalDao.PagrnKeycloakUserRepository;
import br.gov.sead.auth.model.LoginRequest;
import br.gov.sead.auth.model.LoginResponse;
import br.gov.sead.auth.model.ResetPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class LoginService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PagrnKeycloakUserRepository pagrnKeycloakUserRepository;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.keycloak.authorization-grant-type}")
    private String grantType;
    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String tokenUri;

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", grantType);
        map.add("username", loginRequest.getUsername());
        map.add("password", loginRequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(tokenUri, httpEntity, LoginResponse.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<String>  resetPassword(ResetPasswordRequest resetPasswordRequest,String username){

        ResponseEntity<LoginResponse> loginResponse = getAdmTokenExternalRequest();

        List<PagrnKeycloakUser> usuariosBuscados = pagrnKeycloakUserRepository.findByUsername(username);
        if (usuariosBuscados.isEmpty()){
            return new ResponseEntity<>("Usuário não encontrado",HttpStatus.NOT_FOUND);
        }
        PagrnKeycloakUser user = usuariosBuscados.get(0);
        ResponseEntity<String> responseEntity = changePasswordExternalRequest(resetPasswordRequest,user,loginResponse);

        return new ResponseEntity<>( responseEntity.getBody(), HttpStatus.OK);
        //ResponseEntity<List<KeycloakUsert>> responseEntity = restTemplate.exchange("http://localhost:8080/auth/admin/realms/pagrn/users/",HttpMethod.GET,resetEntity,(Class<List<KeycloakUsert>>)(Object)List.class);
        //(new ModelMapper()).map(responseEntity.getBody(),new TypeToken<List<KeycloakUsert>>() {}.getType())!!!
    }

    public ResponseEntity<LoginResponse> getAdmTokenExternalRequest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "admin-cli");
        //map.add("client_secret", "b27dbc90-8866-46e0-abc1-cbaaaf90a6de");
        map.add("client_secret","e642a6bd-e37b-4cab-8e63-22a5a511614a");//a ser incorporado no properties, ADM-CLI client secret
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
        return restTemplate.postForEntity(tokenUri, httpEntity, LoginResponse.class);
    }

    public ResponseEntity<String> changePasswordExternalRequest(ResetPasswordRequest resetPasswordRequest,PagrnKeycloakUser user, ResponseEntity<LoginResponse> loginResponse){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(loginResponse.getBody().getAccess_token());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ResetPasswordRequest> resetEntity = new HttpEntity<>(resetPasswordRequest,headers);
        return restTemplate.exchange("http://localhost:8080/auth/admin/realms/pagrn/users/"+user.getId()+"/reset-password", HttpMethod.PUT,resetEntity,String.class);
    }
}
