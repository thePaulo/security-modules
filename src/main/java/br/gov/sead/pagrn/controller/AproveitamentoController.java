package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Aproveitamento;
import br.gov.sead.pagrn.dto.aproveitamento.AproveitamentoDtoRequest;
import br.gov.sead.pagrn.dto.aproveitamento.AproveitamentoDtoResponse;
import br.gov.sead.pagrn.service.AproveitamentoService;
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
@RequestMapping("/aproveitamentos")
public class AproveitamentoController {

    private final AproveitamentoService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de Cargo
     *
     * @param service
     */
    public AproveitamentoController(AproveitamentoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        TypeMap<AproveitamentoDtoRequest, Aproveitamento> propertyMapper = this.modelMapper.createTypeMap(AproveitamentoDtoRequest.class, Aproveitamento.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Aproveitamento::setId));
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<AproveitamentoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<AproveitamentoDtoResponse> aproveitamentoDtoResponses = service.find(query, pageable)
                .map(aproveitamento -> modelMapper.map(aproveitamento, AproveitamentoDtoResponse.class));
        return new ResponseEntity<>(aproveitamentoDtoResponses,HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um cargo para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<AproveitamentoDtoResponse> insert(@Valid @RequestBody AproveitamentoDtoRequest dto) {
        try {
            Aproveitamento aproveitamento = modelMapper.map(dto, Aproveitamento.class);

            Long idNivelCargo = dto.getNivelCargo();
            Long idVinculo = dto.getVinculo();
            Long idSetor = dto.getSetor();

            Aproveitamento aproveitamentoSaved = service.aproveitar(aproveitamento, idVinculo, idNivelCargo, idSetor);

            AproveitamentoDtoResponse aproveitamentoDtoResponse = modelMapper.map(aproveitamentoSaved, AproveitamentoDtoResponse.class);

            return new ResponseEntity<>(aproveitamentoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }
}
