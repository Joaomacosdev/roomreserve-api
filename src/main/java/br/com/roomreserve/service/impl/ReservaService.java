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
import br.com.roomreserve.service.validator.ReservaValidator;
import br.com.roomreserve.service.validator.SalaValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService implements br.com.roomreserve.service.ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaValidator reservaValidator;
    private final SalaValidator salaValidator;

    public ReservaService(ReservaRepository reservaRepository, SalaRepository salaRepository, UsuarioRepository usuarioRepository, ReservaValidator reservaValidator, SalaValidator salaValidator) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.usuarioRepository = usuarioRepository;
        this.reservaValidator = reservaValidator;
        this.salaValidator = salaValidator;
    }


    @Override
    @Transactional
    public ReservaResponseDTO cadastrarReserva(ReservaRequestDTO requestDTO) {


        Sala sala = salaRepository.findById(requestDTO.salaId()).orElseThrow(() -> new ReservaInvalidaException("Sala não encontrada"));

        Usuario usuario = usuarioRepository.findById(requestDTO.usuarioId()).orElseThrow(() -> new ReservaInvalidaException("usuário não encontrada"));

        reservaValidator.verificarConflito(sala, requestDTO.inicio(), requestDTO.fim());
        reservaValidator.validarReserva(sala, requestDTO.inicio(), requestDTO.fim());
        salaValidator.validarCapacidade(sala);
        salaValidator.reduzirCapacidade(sala);

        var reserva = new Reserva(requestDTO);
        reserva.setSala(sala);
        reserva.setUsuario(usuario);


        reservaRepository.save(reserva);
        return new ReservaResponseDTO(reserva);
    }









}
