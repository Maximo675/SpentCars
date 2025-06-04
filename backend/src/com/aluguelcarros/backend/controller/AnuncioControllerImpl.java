package com.aluguelcarros.backend.controller;

import com.aluguelcarros.backend.repository.AnuncioRepository;
import com.aluguelcarros.backend.repository.CarroRepository;

public class AnuncioControllerImpl extends AnuncioController {
    public AnuncioControllerImpl(AnuncioRepository anuncioRepository, CarroRepository carroRepository) {
        super(anuncioRepository, carroRepository);
    }
}
