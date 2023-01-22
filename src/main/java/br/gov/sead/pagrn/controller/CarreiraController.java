package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.dto.carreira.*;
import br.gov.sead.pagrn.domain.concrets.Carreira;
import br.gov.sead.pagrn.service.CarreiraService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/carreiras")
public class CarreiraController {

    private final CarreiraService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de pessoa física
     *
     * @param service
     */
    public CarreiraController(CarreiraService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<CarreiraDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<CarreiraDtoResponse> carreiraDtoResponses = service.find(query, pageable)
                .map(carreira -> modelMapper.map(carreira, CarreiraDtoResponse.class));
        return new ResponseEntity<>(carreiraDtoResponses,HttpStatus.OK);
    }


    /**
     * Método reponsável por receber um request de uma pessoa física para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<CarreiraDtoResponse> insert(@Valid @RequestBody CarreiraDtoRequest dto) {
        try {
            Carreira carreira = modelMapper.map(dto, Carreira.class);
            Carreira carreiraSaved = service.criarCarreira(carreira, dto.getPccrId());
            CarreiraDtoResponse carreiraDtoResponse = modelMapper.map(carreiraSaved, CarreiraDtoResponse.class);
            return new ResponseEntity<>(carreiraDtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<CarreiraDtoResponse> update(@Valid @RequestBody CarreiraDtoRequest dto, @PathVariable Long id) {
        try {
            Carreira carreira = modelMapper.map(dto, Carreira.class);
            Carreira carreiraSaved = service.atualizarCarreira(id, carreira, dto.getPccrId());
            CarreiraDtoResponse carreiraDtoResponse = modelMapper.map(carreiraSaved, CarreiraDtoResponse.class);
            return new ResponseEntity<>(carreiraDtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar uma instancia de entidade do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }
}
