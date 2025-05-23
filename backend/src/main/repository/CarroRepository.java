package com.aluguelcarros.backend.repository;

import com.aluguelcarros.backend.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    // Podemos adicionar métodos específicos aqui depois, se necessário
}

