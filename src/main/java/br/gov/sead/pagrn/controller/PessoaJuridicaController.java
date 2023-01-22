package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.PessoaJuridica;
import br.gov.sead.pagrn.dto.pessoaJuridica.PessoaJuridicaDtoRequest;
import br.gov.sead.pagrn.dto.pessoaJuridica.PessoaJuridicaDtoResponse;
import br.gov.sead.pagrn.service.PessoaJuridicaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/pessoasjuridicas")
public class PessoaJuridicaController {
    private final PessoaJuridicaService service;

    private final ModelMapper modelMapper;


    /**
     * Construtor do controller de pessoa juridica
     *
     * @param service
     */
    public PessoaJuridicaController(PessoaJuridicaService service) {

        this.service = service;
        this.modelMapper = new ModelMapper();

    }


    /**
     * Função que retorna todas pessoas Jurídicas cadastradas
     */
    @GetMapping

    @ApiOperation(value = "Retorna todos as pessoas Jurídicas.",
            notes = "Função que retorna todas pessoas Jurídicas cadastradas",
            response = PessoaJuridicaDtoResponse.class,
            httpMethod = "GET")
    public ResponseEntity<Page<PessoaJuridicaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<PessoaJuridicaDtoResponse> pessoaJuridicaDtoResponses = service.find(query, pageable)
                .map(pessoaJuridica -> modelMapper.map(pessoaJuridica, PessoaJuridicaDtoResponse.class));
        return new ResponseEntity<>(pessoaJuridicaDtoResponses, HttpStatus.OK);
    }

    /**
     * Inserir uma nova pessoa Jurídica no banco de dados
     *
     * @param dtoRequest
     */
    @PostMapping
    @ApiOperation(value = "Inserir pessoa Jurídica.",
            notes = "Inserir uma nova pessoa Jurídica no banco de dados",
            response = PessoaJuridicaDtoResponse.class,
            httpMethod = "POST")
    public ResponseEntity<PessoaJuridicaDtoResponse> insert(@Valid @RequestBody @ApiParam(value = "DTO de requisição de inserção da pessoa juridica no BD") PessoaJuridicaDtoRequest dtoRequest) {
        PessoaJuridica pessoaJuridica = modelMapper.map(dtoRequest, PessoaJuridica.class);
        PessoaJuridica pessoaJuridicaSaved = service.insert(pessoaJuridica);
        PessoaJuridicaDtoResponse pessoaJuridicaDtoResponse = modelMapper.map(pessoaJuridicaSaved, PessoaJuridicaDtoResponse.class);
        return new ResponseEntity<>(pessoaJuridicaDtoResponse, HttpStatus.CREATED);
    }

    /**
     * Atualizar um servidor que já está cadastrado no banco de dados
     *
     * @param id
     * @param dtoRequest
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar servidor.",
            notes = "Atualizar um servidor que já está cadastrado no banco de dados",
            response = PessoaJuridicaDtoResponse.class,
            httpMethod = "PUT")
    public ResponseEntity<PessoaJuridicaDtoResponse> update(@Valid @RequestBody @ApiParam(value = "DTO de requisição de atualização da pessoa juridica no BD") PessoaJuridicaDtoRequest dtoRequest, @PathVariable @ApiParam(value = "id da pessoa juridica") Long id) {
        try{
            PessoaJuridica pessoaJuridica = modelMapper.map(dtoRequest, PessoaJuridica.class);
            PessoaJuridica pessoaJuridicaUpdated = service.update(id, pessoaJuridica);
            PessoaJuridicaDtoResponse pessoaJuridicaDtoResponse = modelMapper.map(pessoaJuridicaUpdated, PessoaJuridicaDtoResponse.class);
            return new ResponseEntity<>(pessoaJuridicaDtoResponse, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletar uma pessoa jurídica do banco de dados por meio do seu id
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar uma pessoa jurídica.",
            notes = "Deletar uma pessoa jurídica do banco de dados",
            response = PessoaJuridicaDtoResponse.class,
            httpMethod = "DELETE")
    public ResponseEntity<Object> delete(@PathVariable @ApiParam(value = "id da pessoa jurídica") Long id) {
        return ControllerUtil.delete(id, service);
    }
}