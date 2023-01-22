package br.gov.sead.pagrn.errorhandling;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class ControllerAdvisor  extends ResponseEntityExceptionHandler {
    /**
     * Método para capturar exceções de violações de pré condições lançadas pelo sistema
     * @param ex */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {
        logger.info(ex.getClass().getName());
        final String error = ex.getConstraintName();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("error name", ex.getLocalizedMessage());
        body.put("error", error);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Método para capturar desvio de rotas lançadas pelo controlador
     * @param ex
     * */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(final ResponseStatusException ex){
        logger.info(ex.getClass().getName());
        final String error = "";
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("error name", ex.getLocalizedMessage());
        body.put("error", "Object without reference");
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(final ResponseStatusException ex){
        logger.info(ex.getClass().getName());
        final String error = "";
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("error name", ex.getLocalizedMessage());
        body.put("error", "Object without reference");
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

    /*
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final WebRequest request){
        logger.info(ex.getClass().getName());
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error name", ex.getLocalizedMessage());
        body.put("error", error);
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    */
    /**
     * Método para capturar as exceçoes genéricas lançadas pelo sistema
     * @param ex
     * */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex){
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        final String error = "A error has occorred with your request";
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("error name", ex.getLocalizedMessage());
        body.put("error", error);
        return new ResponseEntity<Object>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
