package br.gov.sead.pagrn.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sead.pagrn.dto.regimePrevidenciario.*;
import br.gov.sead.pagrn.domain.concrets.RegimePrevidenciario;

import br.gov.sead.pagrn.service.RegimePrevidenciarioService;

@RestController
@RequestMapping("/regimesprevidenciarios")
public class RegimePrevidenciarioController {
    
    private final RegimePrevidenciarioService service;
    private final ModelMapper modelMapper;

     /**
     * Construtor do controller
     *
     * @param service
     */
    public RegimePrevidenciarioController(RegimePrevidenciarioService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping
    public ResponseEntity<Page<RegimePrevidenciarioDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<RegimePrevidenciarioDtoResponse> dtoResponses = service.find(query, pageable)
                .map(RegimePrevidenciario -> modelMapper.map(RegimePrevidenciario, RegimePrevidenciarioDtoResponse.class));
        return new ResponseEntity<>(dtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber uma request de uma entidade para inserir no sistema
     *
     * @param dtoRequest
     */
    @PostMapping
    public ResponseEntity<RegimePrevidenciarioDtoResponse> insert(@Valid @RequestBody RegimePrevidenciarioDtoRequest dtoRequest) {
        try {
            RegimePrevidenciario regimePrevidenciario = modelMapper.map(dtoRequest, RegimePrevidenciario.class);
            RegimePrevidenciario regimePrevidenciarioSaved = service.insert(regimePrevidenciario);
            RegimePrevidenciarioDtoResponse dtoResponse = modelMapper.map(regimePrevidenciarioSaved, RegimePrevidenciarioDtoResponse.class);
            
            return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por atualizar uma entidade já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<RegimePrevidenciarioDtoResponse> update(@Valid @RequestBody RegimePrevidenciarioDtoRequest dtoRequest, @PathVariable Long id) {
        try {
            RegimePrevidenciario regimePrevidenciario = modelMapper.map(dtoRequest, RegimePrevidenciario.class);
            RegimePrevidenciario regimePrevidenciarioSaved = service.update(id, regimePrevidenciario);
            RegimePrevidenciarioDtoResponse dtoResponse = modelMapper.map(regimePrevidenciarioSaved, RegimePrevidenciarioDtoResponse.class);
            return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar uma entidade do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }
    
}