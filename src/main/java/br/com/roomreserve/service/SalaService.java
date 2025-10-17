package br.com.roomreserve.service;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.dto.request.SalaUpdateRequestDTO;
import br.com.roomreserve.dto.response.SalaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalaService {

    public SalaResponseDTO cadastrarSala(SalaRequestDTO requestDTO);

    public SalaResponseDTO buscarPorId(Long id);

    public Page<SalaResponseDTO> listarTodasSalas(Pageable pageable);

    public SalaResponseDTO atualizarDadosSala(SalaUpdateRequestDTO requestDTO);

    public void desativarSala(Long id);

    public SalaResponseDTO ativarSala(Long id);
}
