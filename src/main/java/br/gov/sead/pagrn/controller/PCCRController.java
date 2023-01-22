package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.PCCR;
import br.gov.sead.pagrn.dto.pccr.PccrDtoRequest;
import br.gov.sead.pagrn.dto.pccr.PccrDtoResponse;
import br.gov.sead.pagrn.service.PCCRService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/pccrs")
public class PCCRController {

    private final PCCRService service;

    private final ModelMapper modelMapper;

    private PCCRController(PCCRService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping
    public ResponseEntity<Page<PccrDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<PccrDtoResponse> pccrDtoResponses = service.find(query, pageable)
                .map(pccr -> modelMapper.map(pccr, PccrDtoResponse.class));
        return new ResponseEntity<>(pccrDtoResponses, HttpStatus.OK);
    }

    public ResponseEntity<PccrDtoResponse> insert(@Valid @RequestBody PccrDtoRequest dto) {
        try {
            PCCR pccr = modelMapper.map(dto, PCCR.class);
            PCCR pccrSaved = service.insert(pccr, dto.getUnidadesOrganizacionaisId());
            PccrDtoResponse pccrDtoResponse = modelMapper.map(pccrSaved, PccrDtoResponse.class);
            return new ResponseEntity<>(pccrDtoResponse, HttpStatus.CREATED);
        }catch(EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<PccrDtoResponse> update(@Valid @RequestBody PccrDtoRequest dto,  @PathVariable Long id) {
        try {

            PCCR pccr = modelMapper.map(dto, PCCR.class);
            PCCR pccrSaved = service.update(id, pccr, dto.getUnidadesOrganizacionaisId());
            PccrDtoResponse pccrDtoResponse = modelMapper.map(pccrSaved, PccrDtoResponse.class);
            return new ResponseEntity<>(pccrDtoResponse, HttpStatus.OK);
        }catch(EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
