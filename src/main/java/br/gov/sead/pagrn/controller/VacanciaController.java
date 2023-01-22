package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Vacancia;
import br.gov.sead.pagrn.dto.vacancia.VacanciaDtoRequest;
import br.gov.sead.pagrn.dto.vacancia.VacanciaDtoResponse;
import br.gov.sead.pagrn.service.VacanciaService;
import org.modelmapper.ModelMapper;

import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/vacancias")
public class VacanciaController {

    private final VacanciaService service;
    private final ModelMapper modelMapper;

    public VacanciaController(VacanciaService service) {

        this.service = service;
        this.modelMapper = new ModelMapper();
        TypeMap<VacanciaDtoRequest, Vacancia> propertyMapper =
                modelMapper.createTypeMap(VacanciaDtoRequest.class, Vacancia.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Vacancia::setId));
    }

    @GetMapping
    public ResponseEntity<Page<VacanciaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<VacanciaDtoResponse> VacanciaDtoResponses = service.find(query, pageable)
                .map(Vacancia -> modelMapper.map(Vacancia, VacanciaDtoResponse.class));
        return new ResponseEntity<>(VacanciaDtoResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VacanciaDtoResponse> insert(@Valid @RequestBody VacanciaDtoRequest dtoRequest) {
        try {
            Vacancia Vacancia = modelMapper.map(dtoRequest, Vacancia.class);

            Long idVinculo = dtoRequest.getVinculo();

            Vacancia VacanciaSaved = service.suspender(Vacancia, idVinculo);
            VacanciaDtoResponse VacanciaDtoResponse = modelMapper.map(VacanciaSaved, VacanciaDtoResponse.class);
            return new ResponseEntity<>(VacanciaDtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
