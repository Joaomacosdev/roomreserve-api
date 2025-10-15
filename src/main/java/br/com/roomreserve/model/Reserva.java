package br.com.roomreserve.model;

import br.com.roomreserve.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_resevas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Column(nullable = false)
    private LocalDate inicio;

    @Column(nullable = false)
    private LocalDate fim;

    @Column(nullable = false, updatable = false)
    private final LocalDate criadoEm = LocalDate.now();

    @Column(nullable = false)
    private Status status;

    public Reserva() {
    }

    public Reserva(Long id, Usuario usuario, Sala sala, LocalDate inicio, LocalDate fim, Status status) {
        this.id = id;
        this.usuario = usuario;
        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Reserva setId(Long id) {
        this.id = id;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Reserva setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Sala getSala() {
        return sala;
    }

    public Reserva setSala(Sala sala) {
        this.sala = sala;
        return this;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public Reserva setInicio(LocalDate inicio) {
        this.inicio = inicio;
        return this;
    }

    public LocalDate getFim() {
        return fim;
    }

    public Reserva setFim(LocalDate fim) {
        this.fim = fim;
        return this;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public Status getStatus() {
        return status;
    }

    public Reserva setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Reserva reserva = (Reserva) object;
        return Objects.equals(getId(), reserva.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
