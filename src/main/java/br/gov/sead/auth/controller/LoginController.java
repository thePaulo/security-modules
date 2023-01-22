package br.gov.sead.auth.controller;

import br.gov.sead.auth.model.LoginRequest;
import br.gov.sead.auth.model.LoginResponse;
import br.gov.sead.auth.model.ResetPasswordRequest;
import br.gov.sead.auth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/reset/{username}")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest,@PathVariable String username){
        //loginService.resetPassword(resetPasswordRequest);
        return loginService.resetPassword(resetPasswordRequest,username);
    }
}
