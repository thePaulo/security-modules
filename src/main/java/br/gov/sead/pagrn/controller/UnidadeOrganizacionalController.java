package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.UnidadeOrganizacional;
import br.gov.sead.pagrn.dto.unidadeOrganizacional.UnidadeOrganizacionalDtoRequest;
import br.gov.sead.pagrn.dto.unidadeOrganizacional.UnidadeOrganizacionalDtoResponse;
import br.gov.sead.pagrn.service.UnidadeOrganizacionalService;
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
@RequestMapping("/uo")
public class UnidadeOrganizacionalController {

    private final UnidadeOrganizacionalService service;
    private final ModelMapper modelMapper;

    public UnidadeOrganizacionalController(UnidadeOrganizacionalService service) {

        this.service = service;
        this.modelMapper = new ModelMapper();
        TypeMap<UnidadeOrganizacionalDtoRequest, UnidadeOrganizacional> propertyMapper =
                modelMapper.createTypeMap(UnidadeOrganizacionalDtoRequest.class, UnidadeOrganizacional.class);
        propertyMapper.addMappings(mapper -> mapper.skip(UnidadeOrganizacional::setId));
    }

    @GetMapping
    public ResponseEntity<Page<UnidadeOrganizacionalDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<UnidadeOrganizacionalDtoResponse> unidadeOrganizacionalDtoResponses = service.find(query, pageable)
                .map(UnidadeOrganizacional -> modelMapper.map(UnidadeOrganizacional, UnidadeOrganizacionalDtoResponse.class));
        return new ResponseEntity<>(unidadeOrganizacionalDtoResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UnidadeOrganizacionalDtoResponse> insert(@Valid @RequestBody UnidadeOrganizacionalDtoRequest dtoRequest) {
        try {
            UnidadeOrganizacional unidadeOrganizacional = modelMapper.map(dtoRequest, UnidadeOrganizacional.class);
            UnidadeOrganizacional UnidadeOrganizacionalSaved = service.insert(unidadeOrganizacional, dtoRequest.getPessoaJuridica());
            UnidadeOrganizacionalDtoResponse unidadeOrganizacionalDtoResponse = modelMapper.map(UnidadeOrganizacionalSaved, UnidadeOrganizacionalDtoResponse.class);
            return new ResponseEntity<>(unidadeOrganizacionalDtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UnidadeOrganizacionalDtoResponse> update(@Valid @RequestBody UnidadeOrganizacionalDtoRequest dtoRequest, @PathVariable Long id) {
        try {
            UnidadeOrganizacional unidadeOrganizacional = modelMapper.map(dtoRequest, UnidadeOrganizacional.class);
            UnidadeOrganizacional UnidadeOrganizacionalSaved = service.update(id, unidadeOrganizacional, dtoRequest.getPessoaJuridica());
            UnidadeOrganizacionalDtoResponse unidadeOrganizacionalDtoResponse = modelMapper.map(UnidadeOrganizacionalSaved, UnidadeOrganizacionalDtoResponse.class);
            return new ResponseEntity<>(unidadeOrganizacionalDtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
