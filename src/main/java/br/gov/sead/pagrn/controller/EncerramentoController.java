package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Encerramento;
import br.gov.sead.pagrn.dto.encerramento.EncerramentoDtoRequest;
import br.gov.sead.pagrn.dto.encerramento.EncerramentoDtoResponse;
import br.gov.sead.pagrn.service.EncerramentoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
@RequestMapping("/encerramentos")
public class EncerramentoController {
    private final EncerramentoService service;

    private ModelMapper modelMapper;

    /**
     * Construtor do controller de Exoneração
     *
     * @param service
     */
    public EncerramentoController(EncerramentoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<EncerramentoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<EncerramentoDtoResponse> encerramentoDtoResponses = service.find(query, pageable)
                .map(encerramento -> modelMapper.map(encerramento, EncerramentoDtoResponse.class));
        return new ResponseEntity<>(encerramentoDtoResponses,HttpStatus.OK);
    }

    /**
     * Método reponsável por realizar a exoneração de um servidor de sua função
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<EncerramentoDtoResponse> insert(@Valid @RequestBody EncerramentoDtoRequest dto) {
        try {
            Long idVinculo = dto.getVinculoId();

            Encerramento Encerramento = modelMapper.map(dto, Encerramento.class);
            Encerramento EncerramentoSaved = service.encerrarContrato(Encerramento, idVinculo);

            EncerramentoDtoResponse EncerramentoDtoResponse = modelMapper.map(EncerramentoSaved, EncerramentoDtoResponse.class);

            return new ResponseEntity<>(EncerramentoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
