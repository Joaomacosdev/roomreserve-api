package br.com.roomreserve.dto.response;

import br.com.roomreserve.model.Reserva;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.model.Usuario;
import br.com.roomreserve.model.enums.Status;

import java.time.LocalDate;

public record ReservaResponseDTO(
        Long id,
        Usuario usuario,
        Sala sala,
        LocalDate inicio,
        LocalDate fim,
        LocalDate criadoEm,
        Status status
        ) {
    public ReservaResponseDTO(Reserva reserva) {
        this(reserva.getId(), reserva.getUsuario(), reserva.getSala(), reserva.getInicio(), reserva.getFim(),
                reserva.getCriadoEm(), reserva.getStatus());
    }
}
