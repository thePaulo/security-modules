package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Reversao;
import br.gov.sead.pagrn.dto.reversao.ReversaoDtoRequest;
import br.gov.sead.pagrn.dto.reversao.ReversaoDtoResponse;
import br.gov.sead.pagrn.service.ReversaoService;
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
@RequestMapping("/reversoes")
public class ReversaoController {

    private final ReversaoService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de Cargo
     *
     * @param service
     */
    public ReversaoController(ReversaoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        TypeMap<ReversaoDtoRequest, Reversao> propertyMapper = this.modelMapper.createTypeMap(ReversaoDtoRequest.class, Reversao.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Reversao::setId));
    }

    /**
     * Método reponsável por listar todos os cargos do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<ReversaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<ReversaoDtoResponse> reversaoDtoResponses = service.find(query, pageable)
                .map(reversao -> modelMapper.map(reversao, ReversaoDtoResponse.class));
        return new ResponseEntity<>(reversaoDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um cargo para inserir no sistema
     *
     * @param dto
     */
    @PostMapping

    public ResponseEntity<ReversaoDtoResponse> insert(@Valid @RequestBody ReversaoDtoRequest dto) {
        try {
            Reversao reversao = modelMapper.map(dto, Reversao.class);

            Long idVinculo = dto.getVinculo();

            Reversao reversaoSaved = service.reverter(reversao, idVinculo);

            ReversaoDtoResponse reversaoDtoResponse = modelMapper.map(reversaoSaved, ReversaoDtoResponse.class);

            return new ResponseEntity<>(reversaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }
}
