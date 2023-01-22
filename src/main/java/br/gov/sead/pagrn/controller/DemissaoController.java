package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Demissao;
import br.gov.sead.pagrn.dto.demissao.DemissaoDtoRequest;
import br.gov.sead.pagrn.dto.demissao.DemissaoDtoResponse;
import br.gov.sead.pagrn.service.DemissaoService;
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
@RequestMapping("/demissoes")
public class DemissaoController {

    private final DemissaoService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de Cargo
     *
     * @param service
     */
    public DemissaoController(DemissaoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        TypeMap<DemissaoDtoRequest, Demissao> propertyMapper = this.modelMapper.createTypeMap(DemissaoDtoRequest.class, Demissao.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Demissao::setId));
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<DemissaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<DemissaoDtoResponse> demissaoDtoResponses = service.find(query, pageable)
                .map(demissao -> modelMapper.map(demissao, DemissaoDtoResponse.class));
        return new ResponseEntity<>(demissaoDtoResponses,HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um cargo para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<DemissaoDtoResponse> insert(@Valid @RequestBody DemissaoDtoRequest dto) {
        try {
            Demissao demissao = modelMapper.map(dto, Demissao.class);

            Long idVinculo = dto.getVinculo();

            Demissao demissaoSaved = service.demitir(demissao, idVinculo);

            DemissaoDtoResponse demissaoDtoResponse = modelMapper.map(demissaoSaved, DemissaoDtoResponse.class);

            return new ResponseEntity<>(demissaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }
}
