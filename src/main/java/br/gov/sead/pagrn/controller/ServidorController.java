package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.Servidor;
import br.gov.sead.pagrn.dto.servidor.ServidorDtoRequest;
import br.gov.sead.pagrn.dto.servidor.ServidorDtoResponse;
import br.gov.sead.pagrn.service.ServidorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/servidores")
public class ServidorController {

    private final ServidorService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de pessoa física
     *
     * @param service
     */
    public ServidorController(ServidorService service) {

        this.service = service;
        this.modelMapper = new ModelMapper();
        TypeMap<ServidorDtoRequest, Servidor> propertyMapper = this.modelMapper.createTypeMap(ServidorDtoRequest.class, Servidor.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Servidor::setId));
    }

    /**
     * Método reponsável por listar todas os servidores do sistema
     */
    @GetMapping
    public ResponseEntity<Page<ServidorDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<ServidorDtoResponse> servidorDtoResponses = service.find(query, pageable)
                .map(servidor-> modelMapper.map(servidor, ServidorDtoResponse.class));
        return new ResponseEntity<>(servidorDtoResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public ResponseEntity<Page<ServidorDtoResponse>> findByCPF(@PathVariable String cpf, Pageable pageable){
        Page<ServidorDtoResponse> servidorDtoResponses = service.findByCPF(cpf, pageable)
                .map(servidor -> modelMapper.map(servidor, ServidorDtoResponse.class));
        return new ResponseEntity<>(servidorDtoResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<Page<ServidorDtoResponse>> findByNome(@PathVariable String nome, Pageable pageable){
        Page<ServidorDtoResponse> servidorDtoResponses = service.findByNome(nome, pageable)
                .map(servidor -> modelMapper.map(servidor, ServidorDtoResponse.class));
        return new ResponseEntity<>(servidorDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por atualizar uma pessoa física já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     */
    @PostMapping
    public ResponseEntity<ServidorDtoResponse> insert(@Valid @RequestBody ServidorDtoRequest dtoRequest){

        Servidor servidor = modelMapper.map(dtoRequest, Servidor.class);
        Servidor servidorSaved = service.insert(servidor, dtoRequest.getPessoaFisica());

        ServidorDtoResponse servidorDtoResponse = modelMapper.map(servidorSaved, ServidorDtoResponse.class);
        return new ResponseEntity<>(servidorDtoResponse, HttpStatus.CREATED);
    }

    /**
     * Método reponsável por atualizar uma pessoa física já cadastrada no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServidorDtoResponse> update(@Valid @RequestBody ServidorDtoRequest dtoRequest, @PathVariable Long id) {
        try{
            Servidor servidor = modelMapper.map(dtoRequest, Servidor.class);
            Servidor servidorUpdated = service.update(id, servidor);
            ServidorDtoResponse servidorDtoResponse = modelMapper.map(servidorUpdated, ServidorDtoResponse.class);
            return new ResponseEntity<>(servidorDtoResponse, HttpStatus.OK);
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
