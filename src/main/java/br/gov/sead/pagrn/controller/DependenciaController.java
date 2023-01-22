package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.Dependencia;
import br.gov.sead.pagrn.dto.dependencia.DependenciaDtoRequest;
import br.gov.sead.pagrn.dto.dependencia.DependenciaDtoResponse;
import br.gov.sead.pagrn.service.DependenciaService;
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
@RequestMapping("/dependencias")
public class DependenciaController {

    private final DependenciaService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de dependencias
     *
     * @param service
     */
    public DependenciaController(DependenciaService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

    }

    @GetMapping
    public ResponseEntity<Page<DependenciaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<DependenciaDtoResponse> dependenciaDtoResponses = service.find(query, pageable)
                .map(dependencia ->  modelMapper.map(dependencia, DependenciaDtoResponse.class));
        return new ResponseEntity<>(dependenciaDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um dependencia para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<DependenciaDtoResponse> insert(@Valid @RequestBody DependenciaDtoRequest dto) {
        Dependencia dependencia = modelMapper.map(dto, Dependencia.class);
        Dependencia dependenciaSaved = service.insert(dependencia, dto.getDependente());

        DependenciaDtoResponse dependenciaDtoResponse = modelMapper.map(dependenciaSaved, DependenciaDtoResponse.class);

        return new ResponseEntity<>(dependenciaDtoResponse, HttpStatus.CREATED);
    }

    /**
     * Método reponsável por atualizar um dependencia já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<DependenciaDtoResponse> update(@Valid @RequestBody DependenciaDtoRequest dtoRequest, @PathVariable Long id) {
        try{
            Dependencia dependencia = modelMapper.map(dtoRequest, Dependencia.class);
            Dependencia dependenciaSaved = service.update(id, dependencia);
            DependenciaDtoResponse dependenciaDtoResponse = modelMapper.map(dependenciaSaved, DependenciaDtoResponse.class);
            return new ResponseEntity<>(dependenciaDtoResponse,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar um dependencia do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }



}
