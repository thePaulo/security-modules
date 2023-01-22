package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.dto.remuneracaoBasica.*;
import br.gov.sead.pagrn.domain.concrets.RemuneracaoBasica;
import br.gov.sead.pagrn.service.RemuneracaoBasicaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/remuneracoes")
public class RemuneracaoBasicaController {

    private final RemuneracaoBasicaService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller da entidade
     *
     * @param service
     */
    public RemuneracaoBasicaController(RemuneracaoBasicaService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar todas as instancias da entidade no sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<RemuneracaoBasicaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<RemuneracaoBasicaDtoResponse> remuneracaoBasicaDtoResponses = service.find(query, pageable)
                .map(cargo -> modelMapper.map(cargo, RemuneracaoBasicaDtoResponse.class));
        return new ResponseEntity<>(remuneracaoBasicaDtoResponses, HttpStatus.OK);
    }


    /**
     * Método reponsável por receber um request de uma instancia de entidade para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<RemuneracaoBasicaDtoResponse> insert(@Valid @RequestBody RemuneracaoBasicaDtoRequest dto) {
        try {
            RemuneracaoBasica entity = modelMapper.map(dto, RemuneracaoBasica.class);
            RemuneracaoBasica entitySaved = service.criarRemuneracao(entity, dto.getCargoId(), dto.getJornadaId(), dto.getNivelDesempenhoId());
            RemuneracaoBasicaDtoResponse remuneracaoBasicaDtoResponse = modelMapper.map(entitySaved, RemuneracaoBasicaDtoResponse.class);
            return new ResponseEntity<>(remuneracaoBasicaDtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemuneracaoBasicaDtoResponse> update(@Valid @RequestBody RemuneracaoBasicaDtoRequest dto,  @PathVariable Long id) {
        try{
            RemuneracaoBasica entity = modelMapper.map(dto, RemuneracaoBasica.class);
            RemuneracaoBasica entitySaved = service.atualizarRemuneracao(id, entity, dto.getCargoId(), dto.getJornadaId(), dto.getNivelDesempenhoId());
            RemuneracaoBasicaDtoResponse remuneracaoBasicaDtoResponse = modelMapper.map(entitySaved, RemuneracaoBasicaDtoResponse.class);
            return new ResponseEntity<>(remuneracaoBasicaDtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar uma instancia de entidade do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }

}
