package br.com.roomreserve.service.impl;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.dto.response.UsuarioResponseDTO;
import br.com.roomreserve.infra.exception.EmailJaCadastradoException;
import br.com.roomreserve.model.Usuario;
import br.com.roomreserve.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements br.com.roomreserve.service.UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    @Transactional
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO requestDTO) {
        validarUsuario(requestDTO);

        var usuario = usuarioRepository.save(new Usuario(requestDTO));

        return new UsuarioResponseDTO(usuario);
    }

    private void validarUsuario(UsuarioRequestDTO requestDTO){
        if (usuarioRepository.existsByEmail(requestDTO.email())){
            throw new EmailJaCadastradoException("O e-mail informado já está cadastrado");
        }
    }
}
