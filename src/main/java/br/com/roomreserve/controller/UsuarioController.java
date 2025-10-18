package br.com.roomreserve.controller;

import br.com.roomreserve.dto.request.UsuarioRequestDTO;
import br.com.roomreserve.dto.request.UsuarioUpdateRequestDTO;
import br.com.roomreserve.dto.response.UsuarioResponseDTO;
import br.com.roomreserve.service.impl.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@Valid @RequestBody UsuarioRequestDTO requestDTO, UriComponentsBuilder uriBuilder){
        var usuario = usuarioService.cadastrarUsuario(requestDTO);
        var uri = uriBuilder.path("api/v1/usuarios/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id){
        var usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> listarTodosUsuarios(Pageable pageable){
        var usuario = usuarioService.listarTodosUsuarios(pageable);
        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDTO> atualizarDadosUsuarios(@Valid @RequestBody UsuarioUpdateRequestDTO requestDTO){
        var usuario = usuarioService.atualizarDadosUsuarios(requestDTO);
        return ResponseEntity.ok().body(usuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> ativarUsuario(@PathVariable Long id){
        var usuario = usuarioService.ativarUsuario(id);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable Long id){
        usuarioService.desativarUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
