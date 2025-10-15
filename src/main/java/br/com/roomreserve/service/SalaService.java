package br.com.roomreserve.service;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.dto.response.SalaResponseDTO;

public interface SalaService {

    public SalaResponseDTO cadastrarSala(SalaRequestDTO requestDTO);
}
