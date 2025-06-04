package com.aluguelcarros.backend.controller;

import com.aluguelcarros.backend.model.Aluguel;
import com.aluguelcarros.backend.model.Anuncio;
import com.aluguelcarros.backend.model.StatusAluguel;
import com.aluguelcarros.backend.repository.AluguelRepository;
import com.aluguelcarros.backend.repository.AnuncioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluguels")
public class AluguelController {

    private final AluguelRepository aluguelRepository;
    private final AnuncioRepository anuncioRepository;

    public AluguelController(AluguelRepository aluguelRepository, AnuncioRepository anuncioRepository) {
        this.aluguelRepository = aluguelRepository;
        this.anuncioRepository = anuncioRepository;
    }

    @GetMapping
    public List<Aluguel> listarTodos() {
        return aluguelRepository.findAll();
    }

    @PostMapping
    public Aluguel criar(@RequestBody Aluguel aluguel) {
        Anuncio anuncio = anuncioRepository.findById(aluguel.getAnuncio().getId())
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        aluguel.setAnuncio(anuncio);
        aluguel.setStatus(StatusAluguel.RESERVADO);

        return aluguelRepository.save(aluguel);
    }

    @GetMapping("/{id}")
    public Aluguel buscarPorId(@PathVariable Long id) {
        return aluguelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
    }
private final UsuarioRepository usuarioRepository;

public AluguelController(AluguelRepository aluguelRepository, AnuncioRepository anuncioRepository, UsuarioRepository usuarioRepository) {
    this.aluguelRepository = aluguelRepository;
    this.anuncioRepository = anuncioRepository;
    this.usuarioRepository = usuarioRepository;
}

    @PutMapping("/{id}/status")
    public Aluguel atualizarStatus(@PathVariable Long id, @RequestParam StatusAluguel status) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));

        aluguel.setStatus(status);
        return aluguelRepository.save(aluguel);
        private final UsuarioRepository usuarioRepository;

public AluguelController(AluguelRepository aluguelRepository, AnuncioRepository anuncioRepository, UsuarioRepository usuarioRepository) {
    this.aluguelRepository = aluguelRepository;
    this.anuncioRepository = anuncioRepository;
    this.usuarioRepository = usuarioRepository;
}

    }
    
}

