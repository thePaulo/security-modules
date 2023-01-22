package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.Pensionista;
import br.gov.sead.pagrn.dto.pensionista.PensionistaDtoRequest;
import br.gov.sead.pagrn.dto.pensionista.PensionistaDtoResponse;
import br.gov.sead.pagrn.service.PensionistaService;
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
@RequestMapping("/pensionistas")
public class PensionistaController {

    private final PensionistaService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de pessoa física
     *
     * @param service
     */
    public PensionistaController(PensionistaService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar os pensionistas do sistema atraves de uma query e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<PensionistaDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<PensionistaDtoResponse> pensionistaDtoResponses = service.find(query, pageable)
                .map(pensionista -> modelMapper.map(pensionista, PensionistaDtoResponse.class));
        return new ResponseEntity<>(pensionistaDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um pensionista para inserir no sistema
     *
     * @param dto
     */
    @PostMapping
    public ResponseEntity<PensionistaDtoResponse> insert(@Valid @RequestBody PensionistaDtoRequest dto) {
        try {
            Pensionista pensionista = modelMapper.map(dto, Pensionista.class);

            Long idVinculo = dto.getVinculo();
            Long idPessoaPensionista =dto.getPessoaPensionista();
            Long idPessoaProcurador = dto.getPessoaProcurador();

            Pensionista pensionistaSaved = service.insert(idVinculo, idPessoaPensionista, idPessoaProcurador, pensionista);
            PensionistaDtoResponse pensionistaDtoResponse = modelMapper.map(pensionistaSaved, PensionistaDtoResponse.class);
            return new ResponseEntity<>(pensionistaDtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }

    /**
     * Método reponsável por atualizar um pensionista já cadastrado no sistema por meio do seu id e enviar ao DtoResponse
     *
     * @param dtoRequest
     * @param id
     */
    @PutMapping("/{id}")
    public ResponseEntity<PensionistaDtoResponse> update(@Valid @RequestBody PensionistaDtoRequest dtoRequest, @PathVariable Long id) {
        try {
            Pensionista pensionista = modelMapper.map(dtoRequest, Pensionista.class);
            Pensionista pensionistaSaved = service.update(pensionista, id);
            PensionistaDtoResponse pensionistaDtoResponse = modelMapper.map(pensionistaSaved, PensionistaDtoResponse.class);
            return new ResponseEntity<>(pensionistaDtoResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método reponsável por deletar um pensionista do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }

}
