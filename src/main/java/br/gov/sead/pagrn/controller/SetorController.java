package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.Endereco;
import br.gov.sead.pagrn.dto.setor.SetorDtoRequest;
import br.gov.sead.pagrn.dto.setor.SetorDtoResponse;
import br.gov.sead.pagrn.domain.concrets.Setor;
import br.gov.sead.pagrn.service.SetorService;
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
@RequestMapping("/setores")
public class SetorController {

    private final SetorService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de pessoa física
     *
     * @param service
     */
    public SetorController(SetorService service) {

        this.service = service;
        this.modelMapper = new ModelMapper();

    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<SetorDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<SetorDtoResponse> setorDtoResponses = service.find(query, pageable)
                .map(Setor -> modelMapper.map(Setor, SetorDtoResponse.class));
        return new ResponseEntity<>(setorDtoResponses,HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de uma pessoa física para inserir no sistema
     *
     * @param dtoRequest
     */
    @PostMapping
    public ResponseEntity<SetorDtoResponse> insert(@Valid @RequestBody SetorDtoRequest dtoRequest) {
        Setor setor = modelMapper.map(dtoRequest, Setor.class);
        Endereco endereco =  this.modelMapper.map(dtoRequest.getEndereco(), Endereco.class);
        Long idSetorSuperior = dtoRequest.getSetorSuperior();
        Long idUnidadeOrganizacional = dtoRequest.getUnidadeOrganizacional();
        Setor setorSaved = service.insert(setor, idSetorSuperior, idUnidadeOrganizacional, endereco);

        SetorDtoResponse setorDtoResponse = modelMapper.map(setorSaved, SetorDtoResponse.class);
        return new ResponseEntity<>(setorDtoResponse, HttpStatus.CREATED);
    }

    /**
     * Método reponsável por atualizar uma pessoa física já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<SetorDtoResponse> update(@Valid @RequestBody SetorDtoRequest dtoRequest, @PathVariable Long id) {
        try{
            Setor setor = modelMapper.map(dtoRequest, Setor.class);
            Setor setorUpdated = service.update(id, setor);

            SetorDtoResponse setorDtoResponse = modelMapper.map(setorUpdated, SetorDtoResponse.class);
            return new ResponseEntity<>(setorDtoResponse, HttpStatus.OK);
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
