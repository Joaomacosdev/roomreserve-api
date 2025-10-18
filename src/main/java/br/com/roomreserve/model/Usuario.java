package br.com.roomreserve.model;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.dto.request.UsuarioUpdateRequestDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 30)
    private String telefone;

    private Boolean ativo;

    public Usuario() {
    }

    public Usuario(UsuarioRequestDTO requestDTO) {
        this.nome = requestDTO.nome();
        this.email = requestDTO.email();
        this.telefone = requestDTO.telefone();
        this.ativo = true;
    }

    public void atualizarDados(UsuarioUpdateRequestDTO requestDTO) {
        if (requestDTO.nome() != null){
            this.nome =requestDTO.nome();
        }
        if (requestDTO.email() != null){
            this.email =requestDTO.email();
        }
        if (requestDTO.telefone() != null){
            this.telefone =requestDTO.telefone();
        }
    }

    public void ativarUsuario() {
        this.ativo = true;
    }

    public void desativarUsuario() {
        this.ativo = false;
    }

    public Long getId() {
        return id;
    }

    public Usuario setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Usuario setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Boolean istAtivo() {
        return ativo;
    }

    public Usuario setAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Usuario usuario = (Usuario) object;
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
