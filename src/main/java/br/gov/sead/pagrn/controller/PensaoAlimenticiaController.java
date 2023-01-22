package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.PensaoAlimenticia;
import br.gov.sead.pagrn.dto.PensaoAlimenticia.PensaoAlimenticiaDtoRequest;
import br.gov.sead.pagrn.dto.PensaoAlimenticia.PensaoAlimenticiaDtoResponse;
import br.gov.sead.pagrn.service.PensaoAlimenticiaService;
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
@RequestMapping("/pensoes_alimenticias")
public class PensaoAlimenticiaController {

    private final PensaoAlimenticiaService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de pensao
     *
     * @param service
     */
    public PensaoAlimenticiaController(PensaoAlimenticiaService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar as pensoes do sistema atraves de uma query e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<PensaoAlimenticiaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<PensaoAlimenticiaDtoResponse> pensaoDtoResponses = service.find(query, pageable)
                .map(pensao -> modelMapper.map(pensao, PensaoAlimenticiaDtoResponse.class));
        return new ResponseEntity<>(pensaoDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de uma pensao para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<PensaoAlimenticiaDtoResponse> insert(@Valid @RequestBody PensaoAlimenticiaDtoRequest dto) {
        try {
            PensaoAlimenticia pensao = modelMapper.map(dto, PensaoAlimenticia.class);

            Long idDependencia = dto.getDependencia();

            PensaoAlimenticia pensaoSaved = service.insert(idDependencia, pensao);
            PensaoAlimenticiaDtoResponse pensaoDtoResponse = modelMapper.map(pensaoSaved, PensaoAlimenticiaDtoResponse.class);
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
    public ResponseEntity<PensaoAlimenticiaDtoResponse> update(@Valid @RequestBody PensaoAlimenticiaDtoRequest dtoRequest, @PathVariable Long id) {
        try {
            PensaoAlimenticia pensao = modelMapper.map(dtoRequest, PensaoAlimenticia.class);
            PensaoAlimenticia pensaoSaved = service.update(id, pensao);
            PensaoAlimenticiaDtoResponse pensaoDtoResponse = modelMapper.map(pensaoSaved, PensaoAlimenticiaDtoResponse.class);
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
