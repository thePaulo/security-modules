package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.Funcao;
import br.gov.sead.pagrn.dto.funcao.FuncaoDtoRequest;
import br.gov.sead.pagrn.dto.funcao.FuncaoDtoResponse;
import br.gov.sead.pagrn.service.FuncaoService;
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
@RequestMapping("/funcoes")
public class FuncaoController {

    private final FuncaoService service;

    private final ModelMapper modelMapper;

    public FuncaoController(FuncaoService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<FuncaoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<FuncaoDtoResponse> funcaoDtoResponses = service.find(query, pageable)
                .map(funcao -> modelMapper.map(funcao, FuncaoDtoResponse.class));
        return new ResponseEntity<>(funcaoDtoResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FuncaoDtoResponse> insert(@Valid @RequestBody FuncaoDtoRequest dto) {
        try {
            Funcao funcao = modelMapper.map(dto, Funcao.class);
            Funcao funcaoSaved = service.insert(funcao, dto.getUnidadeOrganizacionalId(), dto.getCboId(), dto.getTipoGratificacaoId());
            FuncaoDtoResponse funcaoDtoResponse = modelMapper.map(funcaoSaved, FuncaoDtoResponse.class);
            return new ResponseEntity<>(funcaoDtoResponse, HttpStatus.CREATED);
        }catch(EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<FuncaoDtoResponse> update(@Valid @RequestBody FuncaoDtoRequest dto, @PathVariable Long id) {
        try {

            Funcao funcao = modelMapper.map(dto, Funcao.class);
            Funcao funcaoSaved = service.update(id, funcao, dto.getUnidadeOrganizacionalId(), dto.getCboId(), dto.getTipoGratificacaoId());
            FuncaoDtoResponse funcaoDtoResponse = modelMapper.map(funcaoSaved, FuncaoDtoResponse.class);
            return new ResponseEntity<>(funcaoDtoResponse, HttpStatus.OK);
        }catch(EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar uma funcao do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }
}
