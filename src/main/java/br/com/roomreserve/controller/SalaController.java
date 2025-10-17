package br.com.roomreserve.controller;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.dto.request.SalaUpdateRequestDTO;
import br.com.roomreserve.dto.response.SalaResponseDTO;
import br.com.roomreserve.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity<SalaResponseDTO> cadastrarSala(@Valid @RequestBody SalaRequestDTO requestDTO, UriComponentsBuilder  uriBuilder){
        var sala = salaService.cadastrarSala(requestDTO);
        var uri = uriBuilder.path("/api/v1/salas/{id}").buildAndExpand(sala.id()).toUri();
        return ResponseEntity.created(uri).body(sala);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> buscarSalaPorId(@PathVariable Long id){
        var sala = salaService.buscarPorId(id);
        return ResponseEntity.ok().body(sala);
    }

    @GetMapping
    public ResponseEntity<Page<SalaResponseDTO>> listarTodasSalas(Pageable pageable){
        var sala = salaService.listarTodasSalas(pageable);
        return ResponseEntity.ok().body(sala);
    }

    @PutMapping
    public ResponseEntity<SalaResponseDTO> atualizarDadosSala(@Valid @RequestBody SalaUpdateRequestDTO requestDTO){
        var sala = salaService.atualizarDadosSala(requestDTO);
        return ResponseEntity.ok().body(sala);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> ativarSala(@PathVariable Long id){
        var sala = salaService.ativarSala(id);
        return ResponseEntity.ok().body(sala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarSala(@PathVariable Long id){
        salaService.desativarSala(id);
        return ResponseEntity.noContent().build();
    }
}
