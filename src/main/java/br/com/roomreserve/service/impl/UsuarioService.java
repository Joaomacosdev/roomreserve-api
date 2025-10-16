package br.com.roomreserve.service.impl;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.dto.response.UsuarioResponseDTO;
import br.com.roomreserve.model.Usuario;
import br.com.roomreserve.repository.UsuarioRepository;
import br.com.roomreserve.service.validator.UsuarioValidator;
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


}
