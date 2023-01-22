package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.*;

import br.gov.sead.pagrn.dto.rubrica.RubricaDtoRequest;
import br.gov.sead.pagrn.dto.rubrica.RubricaDtoResponse;
import br.gov.sead.pagrn.service.RubricaService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/rubricas")
public class RubricaController {

    private final RubricaService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de rubrica
     *
     * @param service
     */
    public RubricaController(RubricaService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }


    /**
     * Método reponsável por listar as rubricas do sistema atraves de uma query e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<RubricaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<RubricaDtoResponse> rubricaDtoResponses = service.find(query, pageable).
                map(rubrica -> modelMapper.map(rubrica, RubricaDtoResponse.class));
        return new ResponseEntity<>(rubricaDtoResponses, HttpStatus.OK);

    }

    /**
     * Método reponsável por receber um request de uma rubrica para inserir no sistema
     *
     * @param dto
     */
    public ResponseEntity<RubricaDtoResponse> insert(@Valid @RequestBody RubricaDtoRequest dto) {
        try {
            Rubrica rubrica = modelMapper.map(dto, Rubrica.class);
            Rubrica rubricaSaved = service.create(rubrica);

            RubricaDtoResponse rubricaDtoResponse = modelMapper.map(rubricaSaved, RubricaDtoResponse.class);

            return new ResponseEntity<>(rubricaDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Método reponsável por atualizar uma rubrica já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<RubricaDtoResponse> update(@Valid @RequestBody RubricaDtoRequest dtoRequest, @PathVariable Long id) {
        try {
            Rubrica rubrica = modelMapper.map(dtoRequest, Rubrica.class);
            Rubrica rubricaSaved = service.update(id, rubrica);
            RubricaDtoResponse rubricaDtoResponse = modelMapper.map(rubricaSaved, RubricaDtoResponse.class);
            return new ResponseEntity<>(rubricaDtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar uma rubrica do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }

}
