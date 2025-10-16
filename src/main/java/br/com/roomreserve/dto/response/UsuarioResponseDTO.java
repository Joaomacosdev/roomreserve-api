package br.com.roomreserve.dto.response;

import br.com.roomreserve.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone());
    }
}
