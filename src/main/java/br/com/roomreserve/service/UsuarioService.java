package br.com.roomreserve.service;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.dto.request.UsuarioUpdateRequestDTO;
import br.com.roomreserve.dto.response.UsuarioResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO requestDTO);

    public UsuarioResponseDTO buscarPorId(Long id);

    public Page<UsuarioResponseDTO> listarTodosUsuarios(Pageable pageable);

    public UsuarioResponseDTO atualizarDadosUsuarios(UsuarioUpdateRequestDTO requestDTO);

    public UsuarioResponseDTO ativarUsuario(Long id);

    public void desativarUsuario(Long id);
}
