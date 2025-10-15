package br.com.roomreserve.service;

import br.com.roomreserve.dto.request.ReservaRequestDTO;
import br.com.roomreserve.dto.response.ReservaResponseDTO;

public interface ReservaService {

    public ReservaResponseDTO cadastrarReserva(ReservaRequestDTO requestDTO);
}
