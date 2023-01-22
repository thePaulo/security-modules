package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Falecimento;
import br.gov.sead.pagrn.dto.falecimento.FalecimentoDtoRequest;
import br.gov.sead.pagrn.dto.falecimento.FalecimentoDtoResponse;
import br.gov.sead.pagrn.service.FalecimentoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
@RequestMapping("/falecimentos")
public class FalecimentoController {

    private final FalecimentoService service;

    private ModelMapper modelMapper;

    public FalecimentoController(FalecimentoService service) {
        this.service = service;

        this.modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<FalecimentoDtoRequest, Falecimento> propertyMapper = this.modelMapper.createTypeMap(FalecimentoDtoRequest.class, Falecimento.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Falecimento::setId));
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<FalecimentoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<FalecimentoDtoResponse> falecimentoDtoResponses = service.find(query, pageable)
                .map(falecimento -> modelMapper.map(falecimento, FalecimentoDtoResponse.class));
        return new ResponseEntity<>(falecimentoDtoResponses,HttpStatus.OK);
    }

    /**
     * Método reponsável por definir o estado vital de um servidor como morto e inativar todos os seus vínculos
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<FalecimentoDtoResponse> insert(@Valid @RequestBody FalecimentoDtoRequest dto) {
        try {
            Long idServidor = dto.getServidor();

            Falecimento falecimento = modelMapper.map(dto, Falecimento.class);

            Falecimento falecimentoSaved = service.registrarFalecimento(falecimento, idServidor);

            FalecimentoDtoResponse falecimentoDtoResponse = modelMapper.map(falecimentoSaved, FalecimentoDtoResponse.class);

            return new ResponseEntity<>(falecimentoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
