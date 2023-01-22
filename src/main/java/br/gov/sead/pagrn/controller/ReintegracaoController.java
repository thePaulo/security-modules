package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Reintegracao;
import br.gov.sead.pagrn.dto.reintegracao.ReintegracaoDtoRequest;
import br.gov.sead.pagrn.dto.reintegracao.ReintegracaoDtoResponse;
import br.gov.sead.pagrn.service.ReintegracaoService;
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
@RequestMapping("/reintegracao")
public class ReintegracaoController {

    private final ReintegracaoService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de Cargo
     *
     * @param service
     */
    public ReintegracaoController(ReintegracaoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        TypeMap<ReintegracaoDtoRequest, Reintegracao> propertyMapper = this.modelMapper.createTypeMap(ReintegracaoDtoRequest.class, Reintegracao.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Reintegracao::setId));
    }

    /**
     * Método reponsável por listar todos os cargos do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<ReintegracaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<ReintegracaoDtoResponse> reintegracaoDtoResponses = service.find(query, pageable)
                .map(reintegracao -> modelMapper.map(reintegracao, ReintegracaoDtoResponse.class));
        return new ResponseEntity<>(reintegracaoDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um cargo para inserir no sistema
     *
     * @param dto
     */
    @PostMapping

    public ResponseEntity<ReintegracaoDtoResponse> insert(@Valid @RequestBody ReintegracaoDtoRequest dto) {
        try {
            Reintegracao Reintegracao = modelMapper.map(dto, Reintegracao.class);

            Long idVinculo = dto.getVinculo();

            Reintegracao ReintegracaoSaved = service.reintegrar(Reintegracao, idVinculo);

            ReintegracaoDtoResponse ReintegracaoDtoResponse = modelMapper.map(ReintegracaoSaved, ReintegracaoDtoResponse.class);

            return new ResponseEntity<>(ReintegracaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }
}
