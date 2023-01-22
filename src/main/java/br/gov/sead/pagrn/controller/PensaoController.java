package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.Pensao;
import br.gov.sead.pagrn.dto.pensao.PensaoDtoRequest;
import br.gov.sead.pagrn.dto.pensao.PensaoDtoResponse;
import br.gov.sead.pagrn.service.PensaoService;
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
@RequestMapping("/pensoes")
public class PensaoController {

    private final PensaoService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de pensao
     *
     * @param service
     */
    public PensaoController(PensaoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar as pensoes do sistema atraves de uma query e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<PensaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<PensaoDtoResponse> pensaoDtoResponses = service.find(query, pageable)
                .map(pensao -> modelMapper.map(pensao, PensaoDtoResponse.class));
        return new ResponseEntity<>(pensaoDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de uma pensao para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<PensaoDtoResponse> insert(@Valid @RequestBody PensaoDtoRequest dto) {
        try {
            Pensao pensao = modelMapper.map(dto, Pensao.class);

            Long idPensionista = dto.getPensionista();

            Pensao pensaoSaved = service.insert(idPensionista, pensao);
            PensaoDtoResponse pensaoDtoResponse = modelMapper.map(pensaoSaved, PensaoDtoResponse.class);
            return new ResponseEntity<>(pensaoDtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }

    /**
     * Método reponsável por atualizar uma pensao já cadastrado no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<PensaoDtoResponse> update(@Valid @RequestBody PensaoDtoRequest dtoRequest, @PathVariable Long id) {
        try {
            Pensao pensao = modelMapper.map(dtoRequest, Pensao.class);
            Pensao pensaoSaved = service.update(id, pensao);
            PensaoDtoResponse pensaoDtoResponse = modelMapper.map(pensaoSaved, PensaoDtoResponse.class);
            return new ResponseEntity<>(pensaoDtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar uma pensao do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }


}
