package br.com.roomreserve.service.validator;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.infra.exception.EmailJaCadastradoException;
import br.com.roomreserve.model.Usuario;
import br.com.roomreserve.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public UsuarioValidator(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public void validarUsuario(Usuario usuario){
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EmailJaCadastradoException("O e-mail informado já está cadastrado");
        }
    }
}
