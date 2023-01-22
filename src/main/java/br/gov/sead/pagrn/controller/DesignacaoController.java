package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Designacao;
import br.gov.sead.pagrn.dto.designacao.DesignacaoDtoRequest;
import br.gov.sead.pagrn.dto.designacao.DesignacaoDtoResponse;
import br.gov.sead.pagrn.service.DesignacaoService;
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
@RequestMapping("/designacoes")
public class DesignacaoController {

    private final DesignacaoService service;

    private ModelMapper modelMapper;

    /**
     * Construtor do controller de Designação
     *
     * @param service
     */
    public DesignacaoController(DesignacaoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();



        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<DesignacaoDtoRequest, Designacao> propertyMapper = this.modelMapper.createTypeMap(DesignacaoDtoRequest.class, Designacao.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Designacao::setId));
    }

    /**
     * Método reponsável por listar todas as desiginações do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<DesignacaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<DesignacaoDtoResponse> designacaoDtoResponses = service.find(query, pageable)
                .map(designacao -> modelMapper.map(designacao, DesignacaoDtoResponse.class));
        return new ResponseEntity<>(designacaoDtoResponses,HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de uma desiginação para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<DesignacaoDtoResponse> insert(@Valid @RequestBody DesignacaoDtoRequest dto) {
        try {
            Long idVinculo = dto.getVinculoId();
            Long idFuncao = dto.getFuncao();

            Designacao designacao = modelMapper.map(dto, Designacao.class);
            Designacao designacaoSaved = service.designar(designacao,idVinculo,idFuncao);

            DesignacaoDtoResponse designacaoDtoResponse = modelMapper.map(designacaoSaved, DesignacaoDtoResponse.class);

            return new ResponseEntity<>(designacaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
