package com.aluguelcarros.backend.controller;

import com.aluguelcarros.backend.model.Usuario;
import com.aluguelcarros.backend.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/cadastrar")
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioEncontrado.isEmpty()) {
            throw new RuntimeException("Email não encontrado");
        }

        if (!usuarioEncontrado.get().getSenha().equals(usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        return usuarioEncontrado.get(); // devolve dados do usuário (poderia ser só ID)
    }
}

