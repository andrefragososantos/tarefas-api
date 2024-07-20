package com.tarefas.api.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.dto.UsuarioDTO;
import com.tarefas.api.entities.Usuario;
import com.tarefas.api.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {
        return ResponseEntity.ok().body(usuarioService.listarUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable("id") Long id) {
        UsuarioDTO usuario = usuarioService.buscarUsuario(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable("email") String email) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPeloEmail(email);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(usuario.get());
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Usuario>> buscarUsuarioporNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioPeloNome(nome));
    }

    @GetMapping("/data")
    public ResponseEntity<List<Usuario>> filtrarUsuarioPelaDataNascimento(
        @RequestParam("dataInicio") LocalDate dataInicio, 
        @RequestParam("dataFim") LocalDate dataFim) {
            return ResponseEntity.ok().body(usuarioService.buscarUsuarioPelaDataNascimento(dataInicio, dataFim));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable("id") Long id) {
        UsuarioDTO usuario = usuarioService.buscarUsuario(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarioService.deletarUsuario(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuarioAtualizado) {
        UsuarioDTO usuario = usuarioService.buscarUsuario(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarioAtualizado.setId(id);
        return ResponseEntity.ok().body(usuarioService.salvarUsuario(usuarioAtualizado).converterParaDTO());
    }

    
}
