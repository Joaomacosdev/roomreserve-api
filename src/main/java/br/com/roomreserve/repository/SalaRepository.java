package br.com.roomreserve.repository;

import br.com.roomreserve.model.Sala;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    public Boolean existsByNome(String nome);

    Page<Sala> findAllByAtivoTrue(Pageable pageable);
}
