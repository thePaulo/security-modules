package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.events.Faltas;
import br.gov.sead.pagrn.dto.faltas.FaltasDtoRequest;
import br.gov.sead.pagrn.dto.faltas.FaltasDtoResponse;
import br.gov.sead.pagrn.dto.faltas.FaltasDtoResponse;
import br.gov.sead.pagrn.service.FaltasService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/faltas")
public class FaltasController {
    private final FaltasService service;

    private ModelMapper modelMapper;

    /**
     * Construtor do controller de Faltas
     *
     * @param service
     */
    public FaltasController(FaltasService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

    }
    @GetMapping
    public ResponseEntity<Page<FaltasDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<FaltasDtoResponse> faltasDtoResponses = service.find(query, pageable)
                .map(faltas -> modelMapper.map(faltas, FaltasDtoResponse.class));
        return new ResponseEntity<>(faltasDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de faltas para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<FaltasDtoResponse> insert(@Valid @RequestBody FaltasDtoRequest dto) {
        try {
            Faltas faltas = modelMapper.map(dto, Faltas.class);

            Long vinculoId = dto.getVinculoId();

            Faltas faltasSaved = service.insert(faltas, vinculoId);

            FaltasDtoResponse faltasDtoResponse = modelMapper.map(faltasSaved, FaltasDtoResponse.class);

            return new ResponseEntity<>(faltasDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
    }
    }

    /**
     * Método reponsável por atualizar faltas já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<FaltasDtoResponse> update(@Valid @RequestBody FaltasDtoRequest dtoRequest, @PathVariable Long id) {
        try{
            Faltas faltas = modelMapper.map(dtoRequest, Faltas.class);
            faltas.preencher();
            Faltas faltasSaved = service.update(id, faltas);
            FaltasDtoResponse faltasDtoResponse = modelMapper.map(faltasSaved, FaltasDtoResponse.class);

            return new ResponseEntity<>(faltasDtoResponse,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar um cadastro de faltas do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }

}
