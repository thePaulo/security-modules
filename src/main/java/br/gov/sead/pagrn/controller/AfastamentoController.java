package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.dto.afastamento.AfastamentoDtoRequest;
import br.gov.sead.pagrn.dto.afastamento.AfastamentoDtoResponse;
import br.gov.sead.pagrn.domain.events.Afastamento;
import br.gov.sead.pagrn.service.AfastamentoService;
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
@RequestMapping("/afastamentos")
public class AfastamentoController {

    private final AfastamentoService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de pessoa física
     *
     * @param service
     */
    public AfastamentoController(AfastamentoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<AfastamentoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<AfastamentoDtoResponse> afastamentoDtoResponses = service.find(query, pageable)
                .map(afastamento -> modelMapper.map(afastamento, AfastamentoDtoResponse.class));
        return new ResponseEntity<>(afastamentoDtoResponses,HttpStatus.OK);
    }


    /**
     * Método reponsável por receber um request de uma pessoa física para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<AfastamentoDtoResponse> insert(@Valid @RequestBody AfastamentoDtoRequest dto) {
        Afastamento Afastamento = modelMapper.map(dto, Afastamento.class);
        Long idVinculo = dto.getVinculo();
        Afastamento AfastamentoSaved = service.afastar(Afastamento, idVinculo);
        AfastamentoDtoResponse AfastamentoDtoResponse = modelMapper.map(AfastamentoSaved, AfastamentoDtoResponse.class);
        return new ResponseEntity<>(AfastamentoDtoResponse, HttpStatus.CREATED);
    }

    /**
     * Método reponsável por atualizar uma pessoa física já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<AfastamentoDtoResponse> update(@Valid @RequestBody AfastamentoDtoRequest dtoRequest, @PathVariable Long id) {
        try{
            Afastamento Afastamento = modelMapper.map(dtoRequest, Afastamento.class);
            Afastamento AfastamentoUpdated = service.update(id, Afastamento);
            AfastamentoDtoResponse AfastamentoDtoResponse = modelMapper.map(AfastamentoUpdated, AfastamentoDtoResponse.class);
            return new ResponseEntity<>(AfastamentoDtoResponse,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar uma pessoa física do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }
}
