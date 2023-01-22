package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Aposentadoria;
import br.gov.sead.pagrn.dto.aposentadoria.AposentadoriaDtoRequest;
import br.gov.sead.pagrn.dto.aposentadoria.AposentadoriaDtoResponse;
import br.gov.sead.pagrn.service.AposentadoriaService;
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
@RequestMapping("/aposentadorias")
public class AposentadoriaController {

    private final AposentadoriaService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de Aposentadoria
     *
     * @param service
     */
    public AposentadoriaController(AposentadoriaService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        TypeMap<AposentadoriaDtoRequest, Aposentadoria> propertyMapper = this.modelMapper.createTypeMap(AposentadoriaDtoRequest.class, Aposentadoria.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Aposentadoria::setId));
    }

    @GetMapping
    public ResponseEntity<Page<AposentadoriaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<AposentadoriaDtoResponse> aposentadoriaDtoResponses = service.find(query, pageable)
                .map(aposentadoria -> modelMapper.map(aposentadoria, AposentadoriaDtoResponse.class));
        return new ResponseEntity<>(aposentadoriaDtoResponses,HttpStatus.OK);
    }


    /**
     * Método reponsável por receber um request de um Aposentadoria para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<AposentadoriaDtoResponse> insert(@Valid @RequestBody AposentadoriaDtoRequest dto) {
        try {
            Aposentadoria Aposentadoria = modelMapper.map(dto, Aposentadoria.class);

            Long idVinculo = dto.getVinculo();

            Aposentadoria AposentadoriaSaved = service.aposentar(Aposentadoria, idVinculo);

            AposentadoriaDtoResponse AposentadoriaDtoResponse = modelMapper.map(AposentadoriaSaved, AposentadoriaDtoResponse.class);

            return new ResponseEntity<>(AposentadoriaDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }
}
