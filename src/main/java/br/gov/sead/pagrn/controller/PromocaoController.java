package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Promocao;
import br.gov.sead.pagrn.dto.promocao.PromocaoDtoRequest;
import br.gov.sead.pagrn.dto.promocao.PromocaoDtoResponse;
import br.gov.sead.pagrn.service.PromocaoService;
import org.modelmapper.convention.MatchingStrategies;
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
@RequestMapping("/promocoes")
public class PromocaoController {

    private final PromocaoService service;

    private final ModelMapper modelMapper;

    public PromocaoController(PromocaoService service) {

        this.service = service;
        this.modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<PromocaoDtoRequest, Promocao> propertyMapper = this.modelMapper.createTypeMap(PromocaoDtoRequest.class, Promocao.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Promocao::setId));
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<PromocaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<PromocaoDtoResponse> promocaoDtoResponses = service.find(query, pageable)
                .map(promocao -> modelMapper.map(promocao, PromocaoDtoResponse.class));
        return new ResponseEntity<>(promocaoDtoResponses,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PromocaoDtoResponse> insert(@Valid @RequestBody PromocaoDtoRequest dto) {
        try {
            Long idVinculo = dto.getVinculo();
            Long idNivelCargo = dto.getNivelCargo();

            Promocao Promocao = modelMapper.map(dto, Promocao.class);
            Promocao PromocaoSaved = service.promover(Promocao, idNivelCargo, idVinculo);

            PromocaoDtoResponse PromocaoDtoResponse = modelMapper.map(PromocaoSaved, PromocaoDtoResponse.class);

            return new ResponseEntity<>(PromocaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
