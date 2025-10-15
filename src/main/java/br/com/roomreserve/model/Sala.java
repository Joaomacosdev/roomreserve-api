package br.com.roomreserve.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false)
    private Integer capacidade;
    private Boolean ativo;

    public Sala() {
    }


    public Sala(String nome, Integer capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public Sala setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Sala setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public Sala setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
        return this;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public Sala setAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sala sala = (Sala) object;
        return Objects.equals(getId(), sala.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
