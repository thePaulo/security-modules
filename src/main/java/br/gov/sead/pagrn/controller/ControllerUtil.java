package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.service.generic.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerUtil {

    /**
     * Método reponsável por deletar uma entidade do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    public static ResponseEntity<Object> delete(@PathVariable Long id, AbstractService service) {
        try{
            service.delete(id);
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now().toString());
            body.put("message", "object was succesfully deleted.");
            return new ResponseEntity<Object>(body, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
