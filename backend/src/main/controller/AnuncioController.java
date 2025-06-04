package com.aluguelcarros.backend.controller;

import com.aluguelcarros.backend.model.Anuncio;
import com.aluguelcarros.backend.repository.AnuncioRepository;
import com.aluguelcarros.backend.repository.CarroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    private final AnuncioRepository anuncioRepository;
    private final CarroRepository carroRepository;

    public AnuncioController(AnuncioRepository anuncioRepository, CarroRepository carroRepository) {
        this.anuncioRepository = anuncioRepository;
        this.carroRepository = carroRepository;
    }

    @GetMapping
    public List<Anuncio> listarTodos() {
        return anuncioRepository.findAll();
    }

    @PostMapping
    public Anuncio criar(@RequestBody Anuncio anuncio) {
        // Valida se o carro existe
        carroRepository.findById(anuncio.getCarro().getId())
            .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        return anuncioRepository.save(anuncio);
    }

    @GetMapping("/{id}")
    public Anuncio buscarPorId(@PathVariable Long id) {
        return anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        anuncioRepository.deleteById(id);
    }
}

