package br.gov.sead.pagrn.controller;

import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.dto.vinculo.VinculoDtoRequest;
import br.gov.sead.pagrn.dto.vinculo.VinculoDtoResponse;
import br.gov.sead.pagrn.service.VinculoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/vinculos")
public class VinculoController {

    private final ModelMapper modelMapper;

    private final VinculoService service;

    public VinculoController(VinculoService service){
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    /**
     * Método reponsável por listar todas as pessoas físicas do sistema e encaminhar para o DtoResponse
     */
    @GetMapping
    public ResponseEntity<Page<VinculoDtoResponse>> find(
            @RequestParam(value = "q", defaultValue = "") String query,
            Pageable pageable) {
        Page<VinculoDtoResponse> vinculoDtoResponses = service.find(query, pageable)
                .map(vinculo -> modelMapper.map(vinculo, VinculoDtoResponse.class));
        return new ResponseEntity<>(vinculoDtoResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public ResponseEntity<Page<VinculoDtoResponse>> findByCPFdoServidor(@PathVariable String cpf, Pageable pageable){
        Page<VinculoDtoResponse> vinculoDtoResponses = service.findByCPFdoServidor(cpf, pageable)
                .map(vinculo -> modelMapper.map(vinculo, VinculoDtoResponse.class));
        return new ResponseEntity<>(vinculoDtoResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<Page<VinculoDtoResponse>> findByNomeDoServidor(@PathVariable String nome, Pageable pageable){
        Page<VinculoDtoResponse> vinculoDtoResponses = service.findByNomeDoServidor(nome, pageable)
                .map(vinculo -> modelMapper.map(vinculo, VinculoDtoResponse.class));
        return new ResponseEntity<>(vinculoDtoResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/matricula/{matricula}")
    public ResponseEntity<Page<VinculoDtoResponse>> findByMatriculaDoServidor(@PathVariable String matricula, Pageable pageable){
        Page<VinculoDtoResponse> vinculoDtoResponses = service.findByMatriculaDoServidor(matricula, pageable)
                .map(vinculo -> modelMapper.map(vinculo, VinculoDtoResponse.class));
        return new ResponseEntity<>(vinculoDtoResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/eventosPermitidos/{idVinculo}")
    public ResponseEntity<Set<String>> buscarEventosPermitidos(@PathVariable Long idVinculo){
        Set<String> eventosPermitidos = service.buscarEventosPermitidos(idVinculo);
        return new ResponseEntity<>(eventosPermitidos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VinculoDtoResponse> insertVinculo(@Valid @RequestBody VinculoDtoRequest dto){
         Long setorId = dto.getSetorId();
         Long pessoaFisicaContratanteId = dto.getPessoaFisicaContratanteId();
         Long uoPaganteId = dto.getUoPaganteId();
         Long servidorId = dto.getServidorId();
         Vinculo vinculo = modelMapper.map(dto, Vinculo.class);
         Vinculo vinculosaved = service.criarVinculo(vinculo, setorId, pessoaFisicaContratanteId, uoPaganteId, servidorId);
         VinculoDtoResponse resposta = modelMapper.map(vinculosaved, VinculoDtoResponse.class);
         return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
