package br.com.roomreserve.controller;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.dto.response.SalaResponseDTO;
import br.com.roomreserve.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
