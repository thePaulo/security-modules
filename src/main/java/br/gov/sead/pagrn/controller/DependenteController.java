package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.dto.dependente.DependenteDtoRequest;
import br.gov.sead.pagrn.dto.dependente.DependenteDtoResponse;
import br.gov.sead.pagrn.domain.concrets.Dependente;
import br.gov.sead.pagrn.dto.pessoaFisica.PessoaFisicaDtoResponse;
import br.gov.sead.pagrn.dto.servidor.ServidorDtoResponse;
import br.gov.sead.pagrn.service.DependenteService;
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
@RequestMapping("/dependentes")
public class DependenteController {

    private final DependenteService service;
    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de dependentes
     *
     * @param service
     */
    public DependenteController(DependenteService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

    }

    /**
     * Método reponsável por listar todos os dependentes do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<DependenteDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<DependenteDtoResponse> dependenteDtoResponses = service.find(query, pageable)
                .map(dependente ->  modelMapper.map(dependente, DependenteDtoResponse.class));
        return new ResponseEntity<>(dependenteDtoResponses,HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um dependente para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<DependenteDtoResponse> insert(@Valid @RequestBody DependenteDtoRequest dto) {
        Dependente dependente = modelMapper.map(dto, Dependente.class);
        Dependente dependenteSaved = service.insert(dependente, dto.getPessoaFisica(), dto.getServidor());

        DependenteDtoResponse dependenteDtoResponse = modelMapper.map(dependenteSaved, DependenteDtoResponse.class);
        PessoaFisicaDtoResponse pessoaFisicaDtoResponse = modelMapper.map(dependenteSaved.getPessoaFisica(), PessoaFisicaDtoResponse.class);
        ServidorDtoResponse servidorDtoResponse = modelMapper.map(dependenteSaved.getServidor(), ServidorDtoResponse.class);

        dependenteDtoResponse.setPessoaFisica(pessoaFisicaDtoResponse);
        dependenteDtoResponse.setServidor(servidorDtoResponse);

        return new ResponseEntity<>(dependenteDtoResponse, HttpStatus.CREATED);
    }

    /**
     * Método reponsável por atualizar um dependente já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<DependenteDtoResponse> update(@Valid @RequestBody DependenteDtoRequest dtoRequest, @PathVariable Long id) {
        try{
            Dependente dependente = modelMapper.map(dtoRequest, Dependente.class);
            Dependente dependenteSaved = service.update(dependente, id);
            DependenteDtoResponse dependenteDtoResponse = modelMapper.map(dependenteSaved, DependenteDtoResponse.class);
            return new ResponseEntity<>(dependenteDtoResponse,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar um dependente do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }
}
