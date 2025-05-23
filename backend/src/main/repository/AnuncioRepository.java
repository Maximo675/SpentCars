package com.aluguelcarros.backend.repository;

import com.aluguelcarros.backend.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
}
