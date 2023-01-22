package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.concrets.TipoGratificacao;
import br.gov.sead.pagrn.dto.tipoGratificacao.TipoGratificacaoDTORequest;
import br.gov.sead.pagrn.dto.tipoGratificacao.TipoGratificacaoDTOResponse;
import br.gov.sead.pagrn.service.TipoGratificacaoService;
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
@RequestMapping("/tiposgratificacao")
public class TipoGratificacaoController {

    private final TipoGratificacaoService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de TipoGratificacao
     *
     * @param service
     */
    public TipoGratificacaoController(TipoGratificacaoService service){
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar todas os tipos de gratificacao do sistema e encaminhar para o DtoResponse
     */

    @GetMapping
    public ResponseEntity<Page<TipoGratificacaoDTOResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable){

        Page<TipoGratificacaoDTOResponse> tipoGratificacoes = service.find(query, pageable)
                .map(tipoGratificacao -> modelMapper.map(tipoGratificacao, TipoGratificacaoDTOResponse.class));
        return new ResponseEntity<>(tipoGratificacoes, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um tipo de gratificacao para inserir no sistema
     *
     * @param dto
     */
    public ResponseEntity<TipoGratificacaoDTOResponse> insert(@Valid @RequestBody TipoGratificacaoDTORequest dto) {
        try {
            TipoGratificacao tipoGratificacao = modelMapper.map(dto, TipoGratificacao.class);
            TipoGratificacao tipoGratificacaoSaved = service.insert(tipoGratificacao, dto.getRubricaId());

            TipoGratificacaoDTOResponse tipoGratificacaoDtoResponse = modelMapper.map(tipoGratificacaoSaved, TipoGratificacaoDTOResponse.class);

            return new ResponseEntity<>(tipoGratificacaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoGratificacaoDTOResponse> update(@Valid @RequestBody TipoGratificacaoDTORequest dtoRequest, @PathVariable Long id) {
        try {
            TipoGratificacao tipoGratificacao = modelMapper.map(dtoRequest, TipoGratificacao.class);
            TipoGratificacao tipoGratificacaosaved = service.update(id, tipoGratificacao, dtoRequest.getRubricaId());
            TipoGratificacaoDTOResponse tipoGratificacaoDTOResponse = modelMapper.map(tipoGratificacaosaved, TipoGratificacaoDTOResponse.class);
            return new ResponseEntity<>(tipoGratificacaoDTOResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Método reponsável por deletar um tipo de gratificacao do sistema pelo seu id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ControllerUtil.delete(id, service);
    }
}
