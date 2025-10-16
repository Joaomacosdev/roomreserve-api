package br.com.roomreserve.service;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.dto.response.UsuarioResponseDTO;

public interface UsuarioService {

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO requestDTO);
}
