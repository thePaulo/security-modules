package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.dto.grupoOcupacional.*;
import br.gov.sead.pagrn.domain.concrets.GrupoOcupacional;
import br.gov.sead.pagrn.service.GrupoOcupacionalService;

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
@RequestMapping("/grupos_ocupacionais")
public class GrupoOcupacionalController {

    private final GrupoOcupacionalService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller da entidade
     *
     * @param service
     */
    public GrupoOcupacionalController(GrupoOcupacionalService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar todas as instancias da entidade no sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<GrupoOcupacionalDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<GrupoOcupacionalDtoResponse> grupoOcupacionalDtoResponses = service.find(query, pageable)
                .map(cargo -> modelMapper.map(cargo, GrupoOcupacionalDtoResponse.class));
        return new ResponseEntity<>(grupoOcupacionalDtoResponses, HttpStatus.OK);
    }


    /**
     * Método reponsável por receber um request de uma instancia de entidade para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<GrupoOcupacionalDtoResponse> insert(@Valid @RequestBody GrupoOcupacionalDtoRequest dto) {
        try {
            GrupoOcupacional entity = modelMapper.map(dto, GrupoOcupacional.class);
            GrupoOcupacional entitySaved = service.criarGrupo(entity, dto.getPccrId(), dto.getRubricaId());
            GrupoOcupacionalDtoResponse grupoOcupacionalDtoResponse = modelMapper.map(entitySaved, GrupoOcupacionalDtoResponse.class);
            return new ResponseEntity<>(grupoOcupacionalDtoResponse, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoOcupacionalDtoResponse> insert(@Valid @RequestBody GrupoOcupacionalDtoRequest dto, @PathVariable Long id) {
        try {
            GrupoOcupacional entity = modelMapper.map(dto, GrupoOcupacional.class);
            GrupoOcupacional entitySaved = service.atualizarGrupo(id, entity, dto.getPccrId(), dto.getRubricaId());
            GrupoOcupacionalDtoResponse grupoOcupacionalDtoResponse = modelMapper.map(entitySaved, GrupoOcupacionalDtoResponse.class);
            return new ResponseEntity<>(grupoOcupacionalDtoResponse, HttpStatus.CREATED);
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
