package br.com.roomreserve.service.impl;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.dto.request.UsuarioUpdateRequestDTO;
import br.com.roomreserve.dto.response.UsuarioResponseDTO;
import br.com.roomreserve.infra.exception.NotFoundException;
import br.com.roomreserve.model.Usuario;
import br.com.roomreserve.repository.UsuarioRepository;
import br.com.roomreserve.service.validator.UsuarioValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements br.com.roomreserve.service.UsuarioService {

    private final UsuarioRepository usuarioRepository;
    public final UsuarioValidator usuarioValidator;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioValidator usuarioValidator) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioValidator = usuarioValidator;
    }


    @Override
    @Transactional
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO requestDTO) {
        Usuario usuario = new Usuario(requestDTO);
        usuarioValidator.validarUsuario(usuario);
        usuario = usuarioRepository.save(new Usuario(requestDTO));

        return new UsuarioResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Usuário com id: " + id + " não encontrado")
        );
        return new UsuarioResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioResponseDTO> listarTodosUsuarios(Pageable pageable) {
        return usuarioRepository.findAllByAtivoTrue(pageable).map(UsuarioResponseDTO::new);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO atualizarDadosUsuarios(UsuarioUpdateRequestDTO requestDTO) {
        var usuario = usuarioRepository.findById(requestDTO.id()).orElseThrow(
                () -> new NotFoundException("Usuário com id: " + requestDTO.id() + " não encontrado")
        );
        usuario.atualizarDados(requestDTO);
        usuario = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO ativarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Usuário com id: " + id + " não encontrado")
        );

        usuario.ativarUsuario();
        return new UsuarioResponseDTO(usuario);
    }

    @Override
    public void desativarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Usuário com id: " + id + " não encontrado")
        );

        usuario.desativarUsuario();
    }


}
