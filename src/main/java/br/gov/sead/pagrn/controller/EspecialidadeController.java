package br.gov.sead.pagrn.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sead.pagrn.dto.especialidade.*;
import br.gov.sead.pagrn.domain.concrets.Especialidade;

import br.gov.sead.pagrn.service.EspecialidadeService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    private final EspecialidadeService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller
     *
     * @param service
     */
    public EspecialidadeController(EspecialidadeService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping
    public ResponseEntity<Page<EspecialidadeDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<EspecialidadeDtoResponse> dtoResponses = service.find(query, pageable)
                .map(Especialidade -> modelMapper.map(Especialidade, EspecialidadeDtoResponse.class));
        return new ResponseEntity<>(dtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber uma request de uma entidade para inserir no sistema
     *
     * @param dtoRequest
     */
    @PostMapping
    public ResponseEntity<EspecialidadeDtoResponse> insert(@Valid @RequestBody EspecialidadeDtoRequest dtoRequest) {
        try {
            Especialidade especialidade = modelMapper.map(dtoRequest, Especialidade.class);
            Especialidade especialidadeSaved = service.insert(especialidade);
            EspecialidadeDtoResponse dtoResponse = modelMapper.map(especialidadeSaved, EspecialidadeDtoResponse.class);
            
            return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por atualizar uma entidade já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeDtoResponse> update(@Valid @RequestBody EspecialidadeDtoRequest dtoRequest, @PathVariable Long id) {
        try {
            Especialidade especialidade = modelMapper.map(dtoRequest, Especialidade.class);
            Especialidade especialidadeSaved = service.update(id, especialidade);
            EspecialidadeDtoResponse dtoResponse = modelMapper.map(especialidadeSaved, EspecialidadeDtoResponse.class);
            return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar uma entidade do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }
    
}