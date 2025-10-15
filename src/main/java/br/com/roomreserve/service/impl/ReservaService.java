package br.com.roomreserve.service.impl;

import br.com.roomreserve.dto.request.ReservaRequestDTO;
import br.com.roomreserve.dto.response.ReservaResponseDTO;
import br.com.roomreserve.infra.exception.ReservaInvalidaException;
import br.com.roomreserve.model.Reserva;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.model.Usuario;
import br.com.roomreserve.repository.ReservaRepository;
import br.com.roomreserve.repository.SalaRepository;
import br.com.roomreserve.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ReservaService implements br.com.roomreserve.service.ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository, SalaRepository salaRepository, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    @Transactional
    public ReservaResponseDTO cadastrarReserva(ReservaRequestDTO requestDTO) {
        Sala sala = salaRepository.findById(requestDTO.salaId()).orElseThrow(() -> new ReservaInvalidaException("Sala não encontrada"));
        Usuario usuario = usuarioRepository.findById(requestDTO.usuarioId()).orElseThrow(() -> new ReservaInvalidaException("usuário não encontrada"));
        validarReserva(sala, requestDTO.inicio(), requestDTO.fim());

        var reserva = reservaRepository.save(new Reserva(requestDTO));
        return new ReservaResponseDTO(reserva);
    }

    private void validarReserva(Sala sala, LocalDate inicio, LocalDate fim) {
        if (!sala.isAtivo()) {
            throw new ReservaInvalidaException("Não é possível reservar uma sala inativa");

        }
        if (inicio.isAfter(fim) || inicio.isEqual(fim)) {
            throw new ReservaInvalidaException("Não é possível reservar uma sala inativa");

        }
        if (sala.getCapacidade() <= 0) {
            throw new ReservaInvalidaException("Não é possível reservar uma sala inativa");

        }
    }


}
