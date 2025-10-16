package br.com.roomreserve.repository;

import br.com.roomreserve.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Boolean existsByEmail(String email);
}
