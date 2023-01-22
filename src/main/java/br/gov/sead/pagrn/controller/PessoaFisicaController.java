package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.PessoaFisica;
import br.gov.sead.pagrn.domain.concrets.Telefone;
import br.gov.sead.pagrn.dto.pessoaFisica.PessoaFisicaDtoRequest;
import br.gov.sead.pagrn.dto.pessoaFisica.PessoaFisicaDtoResponse;
import br.gov.sead.pagrn.dto.telefone.TelefoneDtoRequest;
import br.gov.sead.pagrn.dto.telefone.TelefoneDtoResponse;
import br.gov.sead.pagrn.service.PessoaFisicaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoasfisicas")
public class PessoaFisicaController {

    private final PessoaFisicaService service;

    private final ModelMapper modelMapper;


    /**
     * Construtor do controller de pessoa física
     *
     * @param service
     */
    public PessoaFisicaController(PessoaFisicaService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }


    /**
     * Método reponsável por listar as pessoas físicas do sistema atravpes de uma query e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<PessoaFisicaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<PessoaFisicaDtoResponse> pessoaFisicaDtoResponses = service.find(query, pageable)
                .map(pessoaFisica -> modelMapper.map(pessoaFisica, PessoaFisicaDtoResponse.class));
        return new ResponseEntity<>(pessoaFisicaDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de uma pessoa física para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<PessoaFisicaDtoResponse> insert(@Valid @RequestBody PessoaFisicaDtoRequest dto) {
        try {
            PessoaFisica pessoaFisica = modelMapper.map(dto, PessoaFisica.class);
            PessoaFisica pessoaFisicaSaved = service.insert(pessoaFisica);
            PessoaFisicaDtoResponse pessoaFisicaDtoResponse = modelMapper.map(pessoaFisicaSaved, PessoaFisicaDtoResponse.class);
            return new ResponseEntity<>(pessoaFisicaDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por atualizar uma pessoa física já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<PessoaFisicaDtoResponse> update(@Valid @RequestBody PessoaFisicaDtoRequest dtoRequest, @PathVariable Long id) {
        try {
            PessoaFisica pessoaFisica = modelMapper.map(dtoRequest, PessoaFisica.class);
            PessoaFisica pessoaFisicaSaved = service.update(id, pessoaFisica);
            PessoaFisicaDtoResponse pessoaFisicaDtoResponse = modelMapper.map(pessoaFisicaSaved, PessoaFisicaDtoResponse.class);
            return new ResponseEntity<>(pessoaFisicaDtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
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
