package com.aluguelcarros.backend.controller;

import com.aluguelcarros.backend.model.Carro;
import com.aluguelcarros.backend.repository.CarroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroRepository carroRepository;

    public CarroController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @GetMapping
    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    @PostMapping
    public Carro cadastrar(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    @GetMapping("/{id}")
    public Carro buscarPorId(@PathVariable Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        carroRepository.deleteById(id);
    }
}

    

