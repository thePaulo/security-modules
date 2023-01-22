package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Reconducao;
import br.gov.sead.pagrn.dto.reconducao.ReconducaoDtoRequest;
import br.gov.sead.pagrn.dto.reconducao.ReconducaoDtoResponse;
import br.gov.sead.pagrn.service.ReconducaoService;
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
@RequestMapping("/reconducoes")
public class ReconducaoController {

    private final ReconducaoService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de Cargo
     *
     * @param service
     */
    public ReconducaoController(ReconducaoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        TypeMap<ReconducaoDtoRequest, Reconducao> propertyMapper = this.modelMapper.createTypeMap(ReconducaoDtoRequest.class, Reconducao.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Reconducao::setId));
    }

    /**
     * Método reponsável por listar todos os cargos do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<ReconducaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<ReconducaoDtoResponse> reconducaoDtoResponses = service.find(query, pageable)
                .map(reconducao -> modelMapper.map(reconducao, ReconducaoDtoResponse.class));
        return new ResponseEntity<>(reconducaoDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um cargo para inserir no sistema
     *
     * @param dto
     */
    @PostMapping

    public ResponseEntity<ReconducaoDtoResponse> insert(@Valid @RequestBody ReconducaoDtoRequest dto) {
        try {
            Reconducao reconducao = modelMapper.map(dto, Reconducao.class);

            Long idVinculo = dto.getVinculo();

            Reconducao reconducaoSaved = service.reconduzir(reconducao, idVinculo);

            ReconducaoDtoResponse reconducaoDtoResponse = modelMapper.map(reconducaoSaved, ReconducaoDtoResponse.class);

            return new ResponseEntity<>(reconducaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }
}
