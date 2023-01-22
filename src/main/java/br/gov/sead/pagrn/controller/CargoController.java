package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.dto.cargo.CargoDtoRequest;
import br.gov.sead.pagrn.dto.cargo.CargoDtoResponse;
import br.gov.sead.pagrn.domain.concrets.Cargo;
import br.gov.sead.pagrn.service.CargoService;
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
@RequestMapping("/cargos")
public class CargoController {

    private final CargoService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de pessoa física
     *
     * @param service
     */
    public CargoController(CargoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<CargoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<CargoDtoResponse> cargoDtoResponses = service.find(query, pageable)
                .map(cargo -> modelMapper.map(cargo, CargoDtoResponse.class));
        return new ResponseEntity<>(cargoDtoResponses,HttpStatus.OK);
    }


    /**
     * Método reponsável por receber um request de uma pessoa física para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<CargoDtoResponse> insert(@Valid @RequestBody CargoDtoRequest dto) {
        try {
            Cargo cargo = modelMapper.map(dto, Cargo.class);
            Cargo cargoSaved = service.criarCargo(cargo, dto.getCboId(), dto.getGrupoOcupacionalId());
            CargoDtoResponse cargoDtoResponse = modelMapper.map(cargoSaved, CargoDtoResponse.class);
            return new ResponseEntity<>(cargoDtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoDtoResponse> update(@Valid @RequestBody CargoDtoRequest dto, @PathVariable Long id) {
        try {
            Cargo cargo = modelMapper.map(dto, Cargo.class);
            Cargo cargoSaved = service.atualizarCargo(id, cargo, dto.getCboId(), dto.getGrupoOcupacionalId());
            CargoDtoResponse cargoDtoResponse = modelMapper.map(cargoSaved, CargoDtoResponse.class);
            return new ResponseEntity<>(cargoDtoResponse, HttpStatus.CREATED);
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
