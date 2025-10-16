package br.com.roomreserve.service.validator;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.infra.exception.EmailJaCadastradoException;
import br.com.roomreserve.model.Usuario;
import br.com.roomreserve.repository.ReservaRepository;
import br.com.roomreserve.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioValidatorTest {

    @Test
    void cenarioValidarUsuario() {

        UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
        UsuarioValidator usuarioValidator = new UsuarioValidator(usuarioRepository);

        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO("João", "joao.marcos@email.com", "11999999999");
        Usuario usuario = new Usuario(requestDTO);

        Mockito.when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);


        Assertions.assertThrows(EmailJaCadastradoException.class, () -> {
            usuarioValidator.validarUsuario(usuario);
        });

    }
}