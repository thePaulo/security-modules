package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Retorno;
import br.gov.sead.pagrn.dto.retorno.RetornoDtoRequest;
import br.gov.sead.pagrn.dto.retorno.RetornoDtoResponse;
import br.gov.sead.pagrn.service.RetornoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


@RestController
@RequestMapping("/retornos")
public class RetornoController {

    private final RetornoService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de Cargo
     *
     * @param service
     */
    public RetornoController(RetornoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        TypeMap<RetornoDtoRequest, Retorno> propertyMapper = this.modelMapper.createTypeMap(RetornoDtoRequest.class, Retorno.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Retorno::setId));
    }

    /**
     * Método reponsável por listar todos os cargos do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<RetornoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<RetornoDtoResponse> retornoDtoResponses = service.find(query, pageable)
                .map(retorno -> modelMapper.map(retorno, RetornoDtoResponse.class));
        return new ResponseEntity<>(retornoDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um cargo para inserir no sistema
     *
     * @param dto
     */
    @PostMapping

    public ResponseEntity<RetornoDtoResponse> insert(@Valid @RequestBody RetornoDtoRequest dto) {
        try {
            Retorno retorno = modelMapper.map(dto, Retorno.class);

            Long idVinculo = dto.getVinculo();

            Retorno retornoSaved = service.retornarAoCargoOuFuncao(retorno, idVinculo);

            RetornoDtoResponse retornoDtoResponse = modelMapper.map(retornoSaved, RetornoDtoResponse.class);

            return new ResponseEntity<>(retornoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }
}
