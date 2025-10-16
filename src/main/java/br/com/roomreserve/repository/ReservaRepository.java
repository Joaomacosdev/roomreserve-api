package br.com.roomreserve.repository;

import br.com.roomreserve.model.Reserva;
import br.com.roomreserve.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Boolean existsBySalaAndInicioLessThanAndFimGreaterThan(Sala sala, LocalDate fim, LocalDate inicio);

}
