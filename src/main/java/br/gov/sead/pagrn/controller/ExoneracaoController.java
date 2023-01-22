package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Exoneracao;
import br.gov.sead.pagrn.dto.exoneracao.ExoneracaoDtoRequest;
import br.gov.sead.pagrn.dto.exoneracao.ExoneracaoDtoResponse;
import br.gov.sead.pagrn.service.ExoneracaoService;
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
@RequestMapping("/exoneracoes")
public class ExoneracaoController {
    private final ExoneracaoService service;

    private ModelMapper modelMapper;

    /**
     * Construtor do controller de Exoneração
     *
     * @param service
     */
    public ExoneracaoController(ExoneracaoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<ExoneracaoDtoRequest, Exoneracao> propertyMapper = this.modelMapper.createTypeMap(ExoneracaoDtoRequest.class, Exoneracao.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Exoneracao::setId));
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<ExoneracaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<ExoneracaoDtoResponse> exoneracaoDtoResponses = service.find(query, pageable)
                .map(exoneracao -> modelMapper.map(exoneracao, ExoneracaoDtoResponse.class));
        return new ResponseEntity<>(exoneracaoDtoResponses,HttpStatus.OK);
    }

    /**
     * Método reponsável por realizar a exoneração de um servidor de sua função
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<ExoneracaoDtoResponse> insert(@Valid @RequestBody ExoneracaoDtoRequest dto) {
        try {
            Long idVinculo = dto.getVinculo();

            Exoneracao exoneracao = modelMapper.map(dto, Exoneracao.class);
            Exoneracao exoneracaoSaved = service.exonerar(exoneracao, idVinculo);

            ExoneracaoDtoResponse exoneracaoDtoResponse = modelMapper.map(exoneracaoSaved, ExoneracaoDtoResponse.class);

            return new ResponseEntity<>(exoneracaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
