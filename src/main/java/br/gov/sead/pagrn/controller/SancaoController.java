package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Sancao;
import br.gov.sead.pagrn.dto.sancao.SancaoDtoRequest;
import br.gov.sead.pagrn.dto.sancao.SancaoDtoResponse;
import br.gov.sead.pagrn.service.SancaoService;
import org.modelmapper.convention.MatchingStrategies;
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
@RequestMapping("/sancoes")
public class SancaoController {

    private final SancaoService service;

    private ModelMapper modelMapper;

    public SancaoController(SancaoService service) {

        this.service = service;
        this.modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<SancaoDtoRequest, Sancao> propertyMapper = this.modelMapper.createTypeMap(SancaoDtoRequest.class, Sancao.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Sancao::setId));
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<SancaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<SancaoDtoResponse> sancaoDtoResponses = service.find(query, pageable)
                .map(sancao -> modelMapper.map(sancao, SancaoDtoResponse.class));
        return new ResponseEntity<>(sancaoDtoResponses,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SancaoDtoResponse> insert(@Valid @RequestBody SancaoDtoRequest dto) {
        try {
            Long idVinculo = dto.getVinculo();

            Sancao sancao = modelMapper.map(dto, Sancao.class);
            Sancao sancaoSaved = service.sancionar(sancao, idVinculo);

            SancaoDtoResponse sancaoDtoResponse = modelMapper.map(sancaoSaved, SancaoDtoResponse.class);

            return new ResponseEntity<>(sancaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
