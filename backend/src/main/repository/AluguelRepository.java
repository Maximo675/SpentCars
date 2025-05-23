package com.aluguelcarros.backend.repository;

import com.aluguelcarros.backend.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}

