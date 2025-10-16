package br.com.roomreserve.controller;

import br.com.roomreserve.dto.request.ReservaRequestDTO;
import br.com.roomreserve.dto.response.ReservaResponseDTO;
import br.com.roomreserve.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> cadastrarReserva(@Valid @RequestBody ReservaRequestDTO requestDTO, UriComponentsBuilder uriBuilder){
        var reserva = reservaService.cadastrarReserva(requestDTO);
        var uri = uriBuilder.path("api/v1/reservas/{id}").buildAndExpand(reserva.id()).toUri();
        return ResponseEntity.created(uri).body(reserva);
    }
}
