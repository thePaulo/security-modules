package br.gov.sead.pagrn.controller;

import br.gov.sead.auth.annotations.GestorRH;
import br.gov.sead.auth.annotations.ValidarEscopo;
import br.gov.sead.auth.annotations.ValidarVinculo;
import br.gov.sead.pagrn.domain.events.ProvimentoDeCargo;
import br.gov.sead.pagrn.domain.events.ProvimentoDeFuncao;
import br.gov.sead.pagrn.dto.provimentoDeCargo.ProvimentoDeCargoDtoRequest;
import br.gov.sead.pagrn.dto.provimentoDeCargo.ProvimentoDeCargoDtoResponse;
import br.gov.sead.pagrn.dto.provimentoDeFuncao.ProvimentoDeFuncaoDtoRequest;
import br.gov.sead.pagrn.dto.provimentoDeFuncao.ProvimentoDeFuncaoDtoResponse;
import br.gov.sead.pagrn.service.ProvimentosService;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/provimentodecargo")
public class ProvimentosController {

    private final ProvimentosService service;

    private final ModelMapper modelMapper;

    /**
     * Construtor do controller de Cargo
     *
     * @param service
     */
    public ProvimentosController(ProvimentosService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Método reponsável por listar todos os cargos do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<ProvimentoDeCargoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<ProvimentoDeCargoDtoResponse> provimentoDeCargoDtoResponses = service.find(query, pageable)
                .map(provimento -> modelMapper.map(provimento, ProvimentoDeCargoDtoResponse.class));
        return new ResponseEntity<>(provimentoDeCargoDtoResponses, HttpStatus.OK);
    }

    /**
     * Método reponsável por receber um request de um cargo para inserir no sistema
     *
     * @param dto
     */
    @PostMapping(path = "/cargo")
    @ValidarVinculo
    @GestorRH
    //@ValidarEscopo
    public ResponseEntity<ProvimentoDeCargoDtoResponse> provimentoDeCargo(@Valid @RequestBody ProvimentoDeCargoDtoRequest dto, HttpServletRequest httpServletRequest) {
        try {
            ProvimentoDeCargo provimentoDeCargo = modelMapper.map(dto, ProvimentoDeCargo.class);
            System.out.println(provimentoDeCargo.getId());
            Long vinculoId = dto.getVinculoId();
            Long pessoaJuridicaContratanteId = dto.getPessoaJuridicaContratanteId();
            Long uoPaganteId = dto.getUoPaganteId();
            Long remuneracaoBasicaId = dto.getRemuneracaoBasicaId();

            ProvimentoDeCargo nomeacaoSaved = service.proverCargo(provimentoDeCargo, vinculoId, pessoaJuridicaContratanteId,
                    uoPaganteId, remuneracaoBasicaId);

            ProvimentoDeCargoDtoResponse nomeacaoDtoResponse = modelMapper.map(nomeacaoSaved, ProvimentoDeCargoDtoResponse.class);

            return new ResponseEntity<>(nomeacaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }

    @PostMapping(path = "/funcao")
    public ResponseEntity<ProvimentoDeFuncaoDtoResponse> provimentoDeFuncao(@Valid @RequestBody ProvimentoDeFuncaoDtoRequest dto, HttpServletRequest servletRequest) {
        try {
            ProvimentoDeFuncao provimentoDeFuncao = modelMapper.map(dto, ProvimentoDeFuncao.class);
            Long vinculoId = dto.getVinculoId();
            Long pessoaJuridicaContratanteId = dto.getPessoaJuridicaContratanteId();
            Long uoPaganteId = dto.getUoPaganteId();
            Long funcaoId = dto.getFuncaoId();

            ProvimentoDeFuncao provimentoDeFuncaoSaved = service.proverFuncao(provimentoDeFuncao, vinculoId, pessoaJuridicaContratanteId,
                    uoPaganteId, funcaoId);

            ProvimentoDeFuncaoDtoResponse provimentoDeFuncaoDtoResponse =
                    modelMapper.map(provimentoDeFuncaoSaved, ProvimentoDeFuncaoDtoResponse.class);

            return new ResponseEntity<>(provimentoDeFuncaoDtoResponse, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
    }
}
